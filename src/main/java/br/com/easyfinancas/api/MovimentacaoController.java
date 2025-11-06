package br.com.easyfinancas.api;

import br.com.easyfinancas.api.dto.MovimentacaoDTO;
import br.com.easyfinancas.api.dto.NovaMovimentacaoRequest;
import br.com.easyfinancas.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService service;
    public MovimentacaoController(MovimentacaoService service) { this.service = service; }

    @GetMapping
    public Page<MovimentacaoDTO> listar(Pageable pageable) { return service.listar(pageable); }

    @GetMapping("{id}")
    public MovimentacaoDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @GetMapping("/conta/{contaId}")
    public Page<MovimentacaoDTO> listarPorConta(@PathVariable Long contaId, Pageable pageable) {
        return service.listarPorConta(contaId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentacaoDTO criar(@RequestBody @Valid NovaMovimentacaoRequest req) { return service.criar(req); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.deletar(id); }
}
