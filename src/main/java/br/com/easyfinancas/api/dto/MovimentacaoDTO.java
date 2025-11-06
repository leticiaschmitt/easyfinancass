package br.com.easyfinancas.api.dto;

import br.com.easyfinancas.domain.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoDTO(
        Long id,
        TipoMovimentacao tipo,
        BigDecimal valor,
        LocalDate data,
        String descricao,
        Long contaId,
        Long categoriaId
) {}
