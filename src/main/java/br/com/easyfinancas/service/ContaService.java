package br.com.easyfinancas.service;

import br.com.easyfinancas.api.dto.AtualizaContaRequest;
import br.com.easyfinancas.api.dto.ContaDTO;
import br.com.easyfinancas.api.dto.NovaContaRequest;
import br.com.easyfinancas.domain.Conta;
import br.com.easyfinancas.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    private final ContaRepository repo;

    public ContaService(ContaRepository repo) {
        this.repo = repo;
    }

    public Page<ContaDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(c -> new ContaDTO(c.getId(), c.getNome(), c.getSaldo()));
    }

    public ContaDTO buscar(Long id) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return new ContaDTO(c.getId(), c.getNome(), c.getSaldo());
    }

    public ContaDTO criar(NovaContaRequest req) {
        if (repo.existsByNomeIgnoreCase(req.nome())) {
            throw new RuntimeException("Já existe uma conta com esse nome");
        }
        var conta = new Conta(req.nome(), req.saldoInicial() != null ? req.saldoInicial() : BigDecimal.ZERO);
        var salva = repo.save(conta);
        return new ContaDTO(salva.getId(), salva.getNome(), salva.getSaldo());
    }

    public ContaDTO atualizar(Long id, AtualizaContaRequest req) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        c.setNome(req.nome());
        c.setSaldo(req.saldo());
        var salva = repo.save(c);
        return new ContaDTO(salva.getId(), salva.getNome(), salva.getSaldo());
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Conta não encontrada");
        repo.deleteById(id);
    }
}
