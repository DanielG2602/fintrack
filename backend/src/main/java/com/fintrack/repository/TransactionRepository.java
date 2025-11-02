package com.fintrack.repository;

import com.fintrack.entity.Transaction;
import com.fintrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndTipo(User user, String tipo);

    List<Transaction> findByUserAndDataTransacaoBetween(User user, LocalDateTime inicio, LocalDateTime fim);

    List<Transaction> findByUserAndTipoAndDataTransacaoBetween(User user, String tipo, LocalDateTime inicio, LocalDateTime fim);
}
