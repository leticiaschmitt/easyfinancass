package br.com.easyfinancas.app;

import br.com.easyfinancas.model.*;
import br.com.easyfinancas.service.RelatorioService;

// >>> IMPORTES NOVOS <<<
import br.com.easyfinancas.dao.ContaDbDao;
import br.com.easyfinancas.model.db.ContaDb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ======== SUA SIMULAÇÃO EXISTENTE (OK) ========
        Usuario usuario = new Usuario("Letícia Rocha", "leticia@email.com");
        usuario.exibirPerfil();

        Categoria catEntrada = new Categoria("Salário", "#32CD32", TipoMovimentacao.ENTRADA);
        Categoria catDespesa = new Categoria("Aluguel", "#00BFFF", TipoMovimentacao.DESPESA);
        catEntrada.cadastrar();
        catDespesa.cadastrar();

        Conta conta1 = new ContaCorrente("0001-9", usuario);
        Conta conta2 = new ContaPoupanca("0002-1", usuario);

        Movimentacao m1 = new Movimentacao(
                "M001", TipoMovimentacao.ENTRADA, new BigDecimal("3500.00"),
                LocalDate.now(), "Salário do mês", catEntrada);
        Movimentacao m2 = new Movimentacao(
                "M002", TipoMovimentacao.DESPESA, new BigDecimal("1200.00"),
                LocalDate.now(), "Aluguel", catDespesa);

        m1.registrar();
        m2.registrar();

        conta1.adicionarMovimentacao(m1);
        conta1.adicionarMovimentacao(m2);
        conta1.calcularSaldo();
        conta1.exibirExtrato();

        conta1.aplicarAtualizacaoMensal();
        conta2.aplicarAtualizacaoMensal();

        RelatorioService rel = new RelatorioService();
        rel.imprimirExtratoDetalhado(conta1);
        rel.calcularTotaisPorTipo(conta1);
        rel.gerarResumoDia(conta1, LocalDate.now());
        rel.exportarCsv(conta1);

        MetaFinanceira meta = new MetaFinanceira(
                "Reserva de emergência",
                new BigDecimal("10000.00"),
                LocalDate.of(2025, 12, 31)
        );
        meta.exibirMeta();
        meta.verificarProgresso(conta1.getSaldo());

        System.out.println("Fim da simulação.");

        // ======== TESTES DO CAPÍTULO 11 (DAO + JDBC) ========
        System.out.println("\n=== TESTES JDBC / ORACLE (TB_CONTA) ===");
        ContaDbDao dao = new ContaDbDao();

        // (opcional) tornar o teste idempotente
        int apagados = dao.clear();
        if (apagados > 0) {
            System.out.println("Tabela TB_CONTA limpa: " + apagados + " registro(s) removido(s).");
        }

        // 1) INSERT de 5 registros
        dao.insert(new ContaDb("Conta Corrente",  2000.00));
        dao.insert(new ContaDb("Poupança",        5000.00));
        dao.insert(new ContaDb("Salário",         3200.00));
        dao.insert(new ContaDb("Investimentos",  15000.00));
        dao.insert(new ContaDb("Carteira",         100.00));

        // 2) GET ALL e impressão
        List<ContaDb> contas = dao.getAll();
        if (contas.isEmpty()) {
            System.out.println("Nenhum registro encontrado na TB_CONTA.");
        } else {
            contas.forEach(System.out::println);
        }
    }
}
