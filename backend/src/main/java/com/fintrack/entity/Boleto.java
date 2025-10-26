package com.fintrack.entity;
import jakarta.persistence.*;
import com.fintrack.entity.User;

@Entity
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoBoleto;
    private float valor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Boleto() {
    }

    public Boleto(String codigoBoleto, float valor, User user) {
        this.codigoBoleto = codigoBoleto;
        this.valor = valor;
        this.user = user;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    public void setCodigoBoleto(String codigoBoleto) {
        this.codigoBoleto = codigoBoleto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
