package br.com.easyfinancas.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CATEGORIA")
@SequenceGenerator(name = "seq_categoria", sequenceName = "SEQ_CATEGORIA", allocationSize = 1)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria")
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(name = "NM_CATEGORIA", nullable = false, length = 120)
    private String nome;

    @Column(name = "COR_HEX", length = 7)
    private String corHex;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 10)
    private TipoMovimentacao tipo;

    @Column(name = "ATIVA", nullable = false)
    private Integer ativa = 1;

    public Categoria() {}
    public Categoria(String nome, String corHex, TipoMovimentacao tipo) {
        this.nome = nome;
        this.corHex = corHex;
        this.tipo = tipo;
        this.ativa = 1;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCorHex() { return corHex; }
    public TipoMovimentacao getTipo() { return tipo; }
    public Integer getAtiva() { return ativa; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCorHex(String corHex) { this.corHex = corHex; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
    public void setAtiva(Integer ativa) { this.ativa = ativa; }
}
