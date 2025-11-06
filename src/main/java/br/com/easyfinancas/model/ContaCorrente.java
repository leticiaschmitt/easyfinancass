package br.com.easyfinancas.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private BigDecimal tarifaMensal = new BigDecimal("19.90");

    public ContaCorrente(String numero, Usuario usuario) {
        super(numero, usuario);
    }

    public ContaCorrente(String numero, Usuario usuario, BigDecimal tarifaMensal) {
        super(numero, usuario);
        this.tarifaMensal = tarifaMensal;
    }

    @Override
    public void aplicarAtualizacaoMensal() {
        System.out.println("Aplicando tarifa mensal de " + brl(tarifaMensal) + " na conta corrente " + getNumero());
        registrarMovimentacaoSistema(
                TipoMovimentacao.DESPESA,
                tarifaMensal,
                "Tarifa mensal de manutenção",
                categoriaSistema("Tarifas", TipoMovimentacao.DESPESA)
        );
    }

    public BigDecimal getTarifaMensal() { return tarifaMensal; }
    public void setTarifaMensal(BigDecimal tarifaMensal) { this.tarifaMensal = tarifaMensal; }
}
