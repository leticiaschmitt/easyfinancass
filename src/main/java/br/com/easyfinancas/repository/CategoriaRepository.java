package br.com.easyfinancas.repository;

import br.com.easyfinancas.domain.Categoria;
import br.com.easyfinancas.domain.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCaseAndTipo(String nome, TipoMovimentacao tipo);
}
