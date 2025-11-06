package br.com.easyfinancas.api.dto;

import br.com.easyfinancas.domain.TipoMovimentacao;

public record CategoriaDTO(Long id, String nome, String corHex, TipoMovimentacao tipo, Integer ativa) {}
