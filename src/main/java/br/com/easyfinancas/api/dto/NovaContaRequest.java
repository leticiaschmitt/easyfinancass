package br.com.easyfinancas.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record NovaContaRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull @DecimalMin(value = "0.00", inclusive = true) BigDecimal saldoInicial
) {}
