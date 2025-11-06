package br.com.easyfinancas.repository;

import br.com.easyfinancas.domain.Movimentacao;
import br.com.easyfinancas.domain.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    Page<Movimentacao> findByConta(Conta conta, Pageable pageable);
}
