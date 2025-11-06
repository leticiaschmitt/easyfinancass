package br.com.easyfinancas.service;

import br.com.easyfinancas.api.dto.MovimentacaoDTO;
import br.com.easyfinancas.api.dto.NovaMovimentacaoRequest;
import br.com.easyfinancas.domain.Categoria;
import br.com.easyfinancas.domain.Conta;
import br.com.easyfinancas.domain.Movimentacao;
import br.com.easyfinancas.repository.CategoriaRepository;
import br.com.easyfinancas.repository.ContaRepository;
import br.com.easyfinancas.repository.MovimentacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repo;
    private final ContaRepository contaRepo;
    private final CategoriaRepository catRepo;

    public MovimentacaoService(MovimentacaoRepository repo, ContaRepository contaRepo, CategoriaRepository catRepo) {
        this.repo = repo;
        this.contaRepo = contaRepo;
        this.catRepo = catRepo;
    }

    public Page<MovimentacaoDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(this::toDto);
    }

    public Page<MovimentacaoDTO> listarPorConta(Long contaId, Pageable pageable) {
        Conta conta = contaRepo.findById(contaId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return repo.findByConta(conta, pageable).map(this::toDto);
    }

    public MovimentacaoDTO buscar(Long id) {
        var m = repo.findById(id).orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
        return toDto(m);
    }

    public MovimentacaoDTO criar(NovaMovimentacaoRequest req) {
        var conta = contaRepo.findById(req.contaId()).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        Categoria categoria = null;
        if (req.categoriaId() != null) {
            categoria = catRepo.findById(req.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        }
        var mov = new Movimentacao(req.tipo(), req.valor(), req.data(), req.descricao(), conta, categoria);
        var salva = repo.save(mov);
        // (Opcional: regra de negócio de atualizar saldo agregado em Conta, se desejar)
        return toDto(salva);
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Movimentação não encontrada");
        repo.deleteById(id);
    }

    private MovimentacaoDTO toDto(Movimentacao m) {
        return new MovimentacaoDTO(
                m.getId(),
                m.getTipo(),
                m.getValor(),
                m.getData(),
                m.getDescricao(),
                m.getConta() != null ? m.getConta().getId() : null,
                m.getCategoria() != null ? m.getCategoria().getId() : null
        );
    }
}
