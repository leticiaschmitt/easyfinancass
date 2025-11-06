package br.com.easyfinancas.api;

import br.com.easyfinancas.api.dto.*;
import br.com.easyfinancas.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;
    public CategoriaController(CategoriaService service) { this.service = service; }

    @GetMapping
    public Page<CategoriaDTO> listar(Pageable pageable) { return service.listar(pageable); }

    @GetMapping("{id}")
    public CategoriaDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO criar(@RequestBody @Valid NovaCategoriaRequest req) { return service.criar(req); }

    @PutMapping("{id}")
    public CategoriaDTO atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaCategoriaRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.deletar(id); }
}
