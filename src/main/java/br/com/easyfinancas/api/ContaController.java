package br.com.easyfinancas.api;

import br.com.easyfinancas.api.dto.AtualizaContaRequest;
import br.com.easyfinancas.api.dto.ContaDTO;
import br.com.easyfinancas.api.dto.NovaContaRequest;
import br.com.easyfinancas.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ContaDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("{id}")
    public ContaDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaDTO criar(@RequestBody @Valid NovaContaRequest req) {
        return service.criar(req);
    }

    @PutMapping("{id}")
    public ContaDTO atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaContaRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
