package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.ItemRefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoRequest;
import br.ufsm.redescomp.nutrigest.dto.RefeicaoResponse;
import br.ufsm.redescomp.nutrigest.service.RefeicaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Transactional
    @PostMapping("/refeicoes")
    public ResponseEntity<Void> adicionarRefeicao(@RequestBody @Valid RefeicaoRequest request, UriComponentsBuilder uriBuilder) {
        var refeicaoId = refeicaoService.adicionarRefeicao(request);
        var uri = uriBuilder.path("/refeicoes/{refeicoesId}").buildAndExpand(refeicaoId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/pessoas/{pessoaId}/refeicoes")
    public ResponseEntity<List<RefeicaoResponse>> getRefeicoesByUsuario(@PathVariable("pessoaId") Long pessoaId) {
        var refeicoes = refeicaoService.getRefeicoesByPessoa(pessoaId);
        return ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<RefeicaoResponse> getRefeicaoById(@PathVariable("refeicaoId") Long id) {
        var refeicoes = refeicaoService.getRefeicaoById(id);
        return ResponseEntity.ok(refeicoes);
    }

    @Transactional
    @PutMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> atualizarRefeicao(@PathVariable("refeicaoId") Long id, @RequestBody @Valid RefeicaoRequest request) {
        refeicaoService.atualizarRefeicao(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> deletarRefeicao(@PathVariable("refeicaoId") Long id) {
        refeicaoService.deletarRefeicao(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping("/refeicoes/{refeicaoId}/itens")
    public ResponseEntity<Void> adicionarItem(@PathVariable("refeicaoId") Long refeicaoId, @RequestBody @Valid ItemRefeicaoRequest request, UriComponentsBuilder uriBuilder) {
        Long itemId = refeicaoService.adicionarItemRefeicao(refeicaoId, request);
        var uri = uriBuilder.path("/itens/{itemId}").buildAndExpand(itemId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<Void> atualizarItem(@PathVariable("itemId") Long id, @RequestBody @Valid ItemRefeicaoRequest request) {
        refeicaoService.atualizarItemRefeicao(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> deletarItem(@PathVariable("itemId") Long id) {
        refeicaoService.deletarItemRefeicao(id);
        return ResponseEntity.noContent().build();
    }
}
