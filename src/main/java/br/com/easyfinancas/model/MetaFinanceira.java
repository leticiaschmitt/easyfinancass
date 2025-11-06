package br.com.easyfinancas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

public class MetaFinanceira {
    private String descricao;
    private BigDecimal valorObjetivo;
    private LocalDate prazo;

    public MetaFinanceira(String descricao, BigDecimal valorObjetivo, LocalDate prazo) {
        this.descricao = descricao;
        this.valorObjetivo = valorObjetivo;
        this.prazo = prazo;
    }

    public void exibirMeta() {
        System.out.println("Meta → '" + descricao + "' | Objetivo: " + brl(valorObjetivo) + " | Prazo: " + prazo);
    }

    public void verificarProgresso(BigDecimal saldoAtual) {
        System.out.println("Progresso da meta '" + descricao + "': saldo atual = "
                + brl(saldoAtual) + " de " + brl(valorObjetivo));
    }

    private String brl(BigDecimal v) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(v);
    }
}
