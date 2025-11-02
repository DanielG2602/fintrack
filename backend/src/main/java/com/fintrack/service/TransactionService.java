package com.fintrack.service;

import com.fintrack.dto.TransactionRequestDTO;
import com.fintrack.dto.TransactionResponseDTO;
import com.fintrack.entity.Transaction;
import com.fintrack.entity.User;
import com.fintrack.repository.TransactionRepository;
import com.fintrack.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {
        User user = getAuthenticatedUser();

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setDescricao(dto.getDescricao());
        transaction.setValor(dto.getValor());
        transaction.setTipo(dto.getTipo());
        transaction.setDataTransacao(dto.getDataTransacao());

        Transaction saved = transactionRepository.save(transaction);

        return new TransactionResponseDTO(
                saved.getId(),
                saved.getDescricao(),
                saved.getValor(),
                saved.getTipo(),
                saved.getDataTransacao()
        );
    }

    public List<TransactionResponseDTO> getUserTransactions(String tipo, LocalDateTime inicio, LocalDateTime fim) {
        User user = getAuthenticatedUser();
        List<Transaction> transactions;

        if (tipo != null && inicio != null && fim != null) {
            transactions = transactionRepository.findByUserAndTipoAndDataTransacaoBetween(user, tipo, inicio, fim);
        } else if (tipo != null) {
            transactions = transactionRepository.findByUserAndTipo(user, tipo);
        } else if (inicio != null && fim != null) {
            transactions = transactionRepository.findByUserAndDataTransacaoBetween(user, inicio, fim);
        } else {
            transactions = transactionRepository.findByUser(user);
        }

        return transactions.stream()
                .map(t -> new TransactionResponseDTO(
                        t.getId(),
                        t.getDescricao(),
                        t.getValor(),
                        t.getTipo(),
                        t.getDataTransacao()
                ))
                .collect(Collectors.toList());
    }
}
