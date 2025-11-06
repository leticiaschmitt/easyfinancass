package br.com.easyfinancas.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_MOVIMENTACAO")
@SequenceGenerator(name = "seq_mov", sequenceName = "SEQ_MOVIMENTACAO", allocationSize = 1)
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mov")
    @Column(name = "ID_MOV")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_MOV", nullable = false, length = 10)
    private TipoMovimentacao tipo;

    @Column(name = "VALOR", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "DT_MOV", nullable = false)
    private LocalDate data;

    @Column(name = "DESCR", length = 255)
    private String descricao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID_CONTA", nullable = false)
    private Conta conta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    private Categoria categoria;

    public Movimentacao() {}
    public Movimentacao(TipoMovimentacao tipo, BigDecimal valor, LocalDate data,
                        String descricao, Conta conta, Categoria categoria) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = (data == null ? LocalDate.now() : data);
        this.descricao = descricao;
        this.conta = conta;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public TipoMovimentacao getTipo() { return tipo; }
    public BigDecimal getValor() { return valor; }
    public LocalDate getData() { return data; }
    public String getDescricao() { return descricao; }
    public Conta getConta() { return conta; }
    public Categoria getCategoria() { return categoria; }

    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public void setData(LocalDate data) { this.data = data; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setConta(Conta conta) { this.conta = conta; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
