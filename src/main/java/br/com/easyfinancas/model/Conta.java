package br.com.easyfinancas.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public abstract class Conta {
    private String numero;
    private Usuario usuario;
    private BigDecimal saldo = BigDecimal.ZERO;
    private final List<Movimentacao> historico = new ArrayList<>();

    protected Conta() { }

    protected Conta(String numero, Usuario usuario) {
        this.numero = numero;
        this.usuario = usuario;
    }

    public abstract void aplicarAtualizacaoMensal();

    public void adicionarMovimentacao(Movimentacao mov) {
        historico.add(mov);
        System.out.println("Adicionando movimentação na conta " + numero
                + " → " + mov.getTipo() + " de " + brl(mov.getValor()));
    }

    public void calcularSaldo() {
        System.out.println("Recalculando saldo da conta " + numero + " com base nas movimentações...");
        BigDecimal total = BigDecimal.ZERO;

        for (Movimentacao mov : historico) {
            if (mov.getTipo() == TipoMovimentacao.ENTRADA) {
                total = total.add(mov.getValor());
            } else if (mov.getTipo() == TipoMovimentacao.CUSTO || mov.getTipo() == TipoMovimentacao.DESPESA) {
                total = total.subtract(mov.getValor());
            }
        }
        this.saldo = total;
        System.out.println("Saldo atualizado: " + brl(saldo));
    }

    public void exibirExtrato() {
        System.out.println("Exibindo extrato da conta " + numero
                + " | titular=" + (usuario != null ? usuario.getNome() : "N/A")
                + " | total lançamentos=" + historico.size());
        System.out.println("Saldo atual: " + brl(saldo));
    }

    public void aplicarLimiteNegativo(BigDecimal limite) {
        System.out.println("Aplicando limite negativo (cheque especial simulado) de " + brl(limite) + " na conta " + numero);
    }

    protected void registrarMovimentacaoSistema(TipoMovimentacao tipo, BigDecimal valor, String descricao, Categoria categoria) {
        BigDecimal v = valor == null ? BigDecimal.ZERO : valor.abs();
        String id = "SYS-" + UUID.randomUUID().toString().substring(0, 8);

        Movimentacao sys = new Movimentacao(
                id, tipo, v, java.time.LocalDate.now(), descricao, categoria
        );
        adicionarMovimentacao(sys);
        calcularSaldo();
    }

    protected Categoria categoriaSistema(String nome, TipoMovimentacao tipo) {
        return new Categoria(nome, "#808080", tipo);
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public BigDecimal getSaldo() { return saldo; }
    protected void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public List<Movimentacao> getHistorico() { return historico; }

    protected String brl(BigDecimal v) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(v);
    }

    @Override
    public String toString() {
        return "Conta{numero='" + numero + "', titular='" +
                (usuario != null ? usuario.getNome() : "N/A") + "', saldo=" + brl(saldo) + "}";
    }
}
