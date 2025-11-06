package br.com.easyfinancas.service;

import br.com.easyfinancas.api.dto.*;
import br.com.easyfinancas.domain.Categoria;
import br.com.easyfinancas.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;
    public CategoriaService(CategoriaRepository repo) { this.repo = repo; }

    public Page<CategoriaDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(c -> new CategoriaDTO(c.getId(), c.getNome(), c.getCorHex(), c.getTipo(), c.getAtiva()));
    }

    public CategoriaDTO buscar(Long id) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(c.getId(), c.getNome(), c.getCorHex(), c.getTipo(), c.getAtiva());
    }

    public CategoriaDTO criar(NovaCategoriaRequest req) {
        if (repo.existsByNomeIgnoreCaseAndTipo(req.nome(), req.tipo()))
            throw new RuntimeException("Já existe categoria com esse nome para o tipo");
        var c = new Categoria(req.nome(), req.corHex(), req.tipo());
        var salva = repo.save(c);
        return new CategoriaDTO(salva.getId(), salva.getNome(), salva.getCorHex(), salva.getTipo(), salva.getAtiva());
    }

    public CategoriaDTO atualizar(Long id, AtualizaCategoriaRequest req) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        c.setNome(req.nome());
        c.setCorHex(req.corHex());
        c.setTipo(req.tipo());
        c.setAtiva(req.ativa());
        var salva = repo.save(c);
        return new CategoriaDTO(salva.getId(), salva.getNome(), salva.getCorHex(), salva.getTipo(), salva.getAtiva());
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Categoria não encontrada");
        repo.deleteById(id);
    }
}
