package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.RefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoResponse;
import br.ufsm.redescomp.nutrigest.service.RefeicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RefeicaoController {

    private final RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping("/refeicoes")
    public ResponseEntity<Void> adicionarRefeicao(@RequestBody RefeicaoRequest request, UriComponentsBuilder uriBuilder) {
        var refeicaoId = refeicaoService.adicionarRefeicao(request);
        var uri = uriBuilder.path("/refeicoes/{refeicoesId}").buildAndExpand(refeicaoId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/usuarios/{usuarioId}/refeicoes")
    public ResponseEntity<List<RefeicaoResponse>> getRefeicoesByUsuario(@PathVariable("usuarioId") Long pessoaId) {
        var refeicoes = refeicaoService.getRefeicoesByPessoa(pessoaId);
        return ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<RefeicaoResponse> getRefeicaoById(@PathVariable("refeicaoId") Long id) {
        var refeicoes = refeicaoService.getRefeicaoById(id);
        return ResponseEntity.ok(refeicoes);
    }

    @PutMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> atualizarRefeicao(@PathVariable("refeicaoId") Long id, @RequestBody RefeicaoRequest request) {
        refeicaoService.atualizarRefeicao(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> deletarRefeicao(@PathVariable("refeicaoId") Long id) {
        refeicaoService.deletarRefeicao(id);
        return ResponseEntity.noContent().build();
    }
}
