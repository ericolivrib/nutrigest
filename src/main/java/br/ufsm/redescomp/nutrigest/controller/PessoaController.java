package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.PessoaRequest;
import br.ufsm.redescomp.nutrigest.dto.PessoaResponse;
import br.ufsm.redescomp.nutrigest.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/v1/pessoas")
    public ResponseEntity<Void> adicionarPessoa(@RequestBody PessoaRequest request, UriComponentsBuilder uriBuilder) {
        var pessoaId = pessoaService.adicionarPessoa(request);
        var location = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/v1/pessoas")
    public ResponseEntity<List<PessoaResponse>> getPessoas() {
        var response = pessoaService.getPessoas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v1/pessoas/{id}")
    public ResponseEntity<PessoaResponse> getPessoaById(@PathVariable("id") Long id) {
        var response = pessoaService.getPessoaById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/v1/pessoas/{id}")
    public ResponseEntity<Void> atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaRequest request) {
        pessoaService.atualizarPessoa(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v1/pessoas/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable("id") Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

}
