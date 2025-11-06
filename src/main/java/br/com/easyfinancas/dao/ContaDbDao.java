package br.com.easyfinancas.dao;

import br.com.easyfinancas.model.db.ContaDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDbDao {

    /** Remove todos os registros da TB_CONTA (útil para testes idempotentes). */
    public int clear() {
        final String sql = "DELETE FROM TB_CONTA";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao limpar TB_CONTA: " + e.getMessage());
            return 0;
        }
    }

    /** Insere uma conta usando a SEQ_CONTA; retorna qtd de linhas afetadas (1 se OK). */
    public int insert(ContaDb conta) {
        final String sql =
            "INSERT INTO TB_CONTA (ID_CONTA, NM_CONTA, SALDO) " +
            "VALUES (SEQ_CONTA.NEXTVAL, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, conta.getNome());
            ps.setDouble(2, conta.getSaldo());
            return ps.executeUpdate();

        } catch (SQLSyntaxErrorException e) {
            System.err.println("Tabela/coluna/sequence incorreta: " + e.getMessage());
        } catch (SQLRecoverableException e) {
            System.err.println("Banco indisponível/credenciais: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Falha ao inserir conta: " + e.getMessage());
        }
        return 0;
    }

    /** Consulta todas as contas ordenadas por ID. */
    public List<ContaDb> getAll() {
        final String sql = "SELECT ID_CONTA, NM_CONTA, SALDO FROM TB_CONTA ORDER BY ID_CONTA";
        List<ContaDb> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("ID_CONTA");
                String nome = rs.getString("NM_CONTA");
                double saldo = rs.getDouble("SALDO");
                lista.add(new ContaDb(id, nome, saldo));
            }
        } catch (SQLException e) {
            System.err.println("Falha ao consultar contas: " + e.getMessage());
        }
        return lista;
    }
}
