package br.com.easyfinancas.api.dto;

import br.com.easyfinancas.domain.TipoMovimentacao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovaMovimentacaoRequest(
        @NotNull TipoMovimentacao tipo,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
        LocalDate data,
        @Size(max = 255) String descricao,
        @NotNull Long contaId,
        Long categoriaId
) {}
