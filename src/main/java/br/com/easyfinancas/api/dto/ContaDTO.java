package br.com.easyfinancas.api.dto;

import java.math.BigDecimal;

public record ContaDTO(Long id, String nome, BigDecimal saldo) {}
