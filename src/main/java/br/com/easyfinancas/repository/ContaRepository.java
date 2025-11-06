package br.com.easyfinancas.repository;

import br.com.easyfinancas.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
