package br.com.easyfinancas.api.dto;

import br.com.easyfinancas.domain.TipoMovimentacao;
import jakarta.validation.constraints.*;

public record NovaCategoriaRequest(
        @NotBlank @Size(max = 120) String nome,
        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Use formato #RRGGBB") String corHex,
        @NotNull TipoMovimentacao tipo
) {}
