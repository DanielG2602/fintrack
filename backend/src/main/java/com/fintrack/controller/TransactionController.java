package com.fintrack.controller;

import com.fintrack.dto.TransactionRequestDTO;
import com.fintrack.dto.TransactionResponseDTO;
import com.fintrack.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getUserTransactions(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(transactionService.getUserTransactions(tipo, inicio, fim));
    }
}
