package br.com.easyfinancas.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CONTA")
@SequenceGenerator(name = "seq_conta", sequenceName = "SEQ_CONTA", allocationSize = 1)
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta")
    @Column(name = "ID_CONTA")
    private Long id;

    @Column(name = "NM_CONTA", nullable = false, length = 120)
    private String nome;

    @Column(name = "SALDO", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo;

    public Conta() { }

    public Conta(String nome, BigDecimal saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
}
