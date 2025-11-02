package com.fintrack.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String tipo;
    private LocalDateTime dataTransacao;

    // construtor
    public TransactionResponseDTO(Long id, String descricao, BigDecimal valor, String tipo, LocalDateTime dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataTransacao = dataTransacao;
    }

    // getters
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public String getTipo() { return tipo; }
    public LocalDateTime getDataTransacao() { return dataTransacao; }
}
