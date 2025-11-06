package br.com.easyfinancas.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {
    private BigDecimal taxaRendimentoMensal = new BigDecimal("0.005"); // 0,5% a.m.

    public ContaPoupanca(String numero, Usuario usuario) {
        super(numero, usuario);
    }

    public ContaPoupanca(String numero, Usuario usuario, BigDecimal taxaRendimentoMensal) {
        super(numero, usuario);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    @Override
    public void aplicarAtualizacaoMensal() {
        BigDecimal rendimento = getSaldo()
                .multiply(taxaRendimentoMensal)
                .setScale(2, RoundingMode.HALF_UP);

        System.out.println("Aplicando rendimento de " 
                + taxaRendimentoMensal.multiply(new BigDecimal("100")) + "% na conta poupança " + getNumero());

        if (rendimento.signum() > 0) {
            registrarMovimentacaoSistema(
                    TipoMovimentacao.ENTRADA,
                    rendimento,
                    "Rendimento mensal da poupança",
                    categoriaSistema("Rendimentos", TipoMovimentacao.ENTRADA)
            );
        } else {
            System.out.println("Sem rendimento (saldo zero ou negativo).");
        }
    }

    public BigDecimal getTaxaRendimentoMensal() { return taxaRendimentoMensal; }
    public void setTaxaRendimentoMensal(BigDecimal v) { this.taxaRendimentoMensal = v; }
}
