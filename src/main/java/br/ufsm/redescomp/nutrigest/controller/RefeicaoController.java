package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.RefeicaoDto;
import br.ufsm.redescomp.nutrigest.model.ItemRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;
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
    public ResponseEntity<Void> adicionarRefeicao(@RequestBody @Valid Refeicao refeicao, UriComponentsBuilder uriBuilder) {
        refeicaoService.adicionarRefeicao(refeicao);
        var uri = uriBuilder.path("/refeicoes/{refeicoesId}").buildAndExpand(refeicao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/pessoas/{pessoaId}/refeicoes")
    public ResponseEntity<List<RefeicaoDto>> getRefeicoesByUsuario(@PathVariable("pessoaId") Long pessoaId) {
        var refeicoes = refeicaoService.getRefeicoesByPessoa(pessoaId);
        return ResponseEntity.ok(refeicoes);
    }

    @GetMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<RefeicaoDto> getRefeicaoById(@PathVariable("refeicaoId") Long id) {
        var refeicoes = refeicaoService.getRefeicaoById(id);
        return ResponseEntity.ok(refeicoes);
    }

    @Transactional
    @PutMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> atualizarRefeicao(@PathVariable("refeicaoId") Long id, @RequestBody @Valid Refeicao refeicao) {
        refeicaoService.atualizarRefeicao(id, refeicao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/refeicoes/{refeicaoId}")
    public ResponseEntity<Void> deletarRefeicao(@PathVariable("refeicaoId") Long id) {
        refeicaoService.deletarRefeicao(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping("/refeicoes/{refeicaoId}/itens")
    public ResponseEntity<Void> adicionarItem(@PathVariable("refeicaoId") Long refeicaoId, @RequestBody @Valid ItemRefeicao item, UriComponentsBuilder uriBuilder) {
        refeicaoService.adicionarItemRefeicao(refeicaoId, item);
        var uri = uriBuilder.path("/itens/{itemId}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<Void> atualizarItem(@PathVariable("itemId") Long id, @RequestBody @Valid ItemRefeicao item) {
        refeicaoService.atualizarItemRefeicao(id, item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> deletarItem(@PathVariable("itemId") Long id) {
        refeicaoService.deletarItemRefeicao(id);
        return ResponseEntity.noContent().build();
    }
}
