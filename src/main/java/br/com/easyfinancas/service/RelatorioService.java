package br.com.easyfinancas.service;

import br.com.easyfinancas.model.Conta;
import br.com.easyfinancas.model.Movimentacao;
import br.com.easyfinancas.model.TipoMovimentacao;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class RelatorioService {

    public void gerarResumoDia(Conta conta, LocalDate dia) {
        System.out.println("Gerando resumo do dia " + dia + " para a conta " + conta.getNumero());
    }

    public void gerarResumoPeriodo(Conta conta, LocalDate inicio, LocalDate fim) {
        System.out.println("Gerando resumo do período " + inicio + " até " + fim + " para a conta " + conta.getNumero());
    }

    public void calcularTotaisPorTipo(Conta conta) {
        BigDecimal totEntrada = BigDecimal.ZERO;
        BigDecimal totCusto   = BigDecimal.ZERO;
        BigDecimal totDespesa = BigDecimal.ZERO;

        for (Movimentacao m : conta.getHistorico()) {
            if (m.getTipo() == TipoMovimentacao.ENTRADA) {
                totEntrada = totEntrada.add(m.getValor());
            } else if (m.getTipo() == TipoMovimentacao.CUSTO) {
                totCusto = totCusto.add(m.getValor());
            } else if (m.getTipo() == TipoMovimentacao.DESPESA) {
                totDespesa = totDespesa.add(m.getValor());
            }
        }

        NumberFormat brl = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Totais por tipo → ENTRADA: " + brl.format(totEntrada)
                + " | CUSTO: " + brl.format(totCusto)
                + " | DESPESA: " + brl.format(totDespesa));
    }

    public void exportarCsv(Conta conta) {
        System.out.println("Exportando relatório da conta " + conta.getNumero() + " em formato CSV (simulado).");
    }

    public void imprimirExtratoDetalhado(Conta conta) {
        NumberFormat brl = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("===== EXTRATO DETALHADO (" + conta.getNumero() + ") =====");
        if (conta.getHistorico().isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada.");
            return;
        }
        for (Movimentacao m : conta.getHistorico()) {
            System.out.println(
                m.getData() + " | " + m.getTipo() + " | " + brl.format(m.getValor())
                + " | " + (m.getCategoria() != null ? m.getCategoria().getNome() : "Sem categoria")
                + " | " + m.getDescricao()
            );
        }
        System.out.println("===============================================");
    }
}
