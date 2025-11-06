package br.com.easyfinancas.model.db;

public class ContaDb {
    private Long id;
    private String nome;
    private Double saldo;

    public ContaDb() {}
    public ContaDb(Long id, String nome, Double saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }
    public ContaDb(String nome, Double saldo) { this(null, nome, saldo); }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getSaldo() { return saldo; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return String.format("%d | %s | R$ %.2f", id, nome, saldo);
    }
}
