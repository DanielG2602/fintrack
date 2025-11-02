package com.fintrack.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequestDTO {
    private String descricao;
    private BigDecimal valor;
    private String tipo; // ENTRADA ou SAIDA
    private LocalDateTime dataTransacao;

    // getters e setters
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataTransacao() { return dataTransacao; }
    public void setDataTransacao(LocalDateTime dataTransacao) { this.dataTransacao = dataTransacao; }
}
