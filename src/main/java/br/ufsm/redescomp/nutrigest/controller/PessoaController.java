package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.PessoaDto;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> adicionarPessoa(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        pessoaService.adicionarPessoa(pessoa);
        var location = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> getPessoas() {
        List<PessoaDto> pessoas = pessoaService.getPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaDto> getPessoaById(@PathVariable("pessoaId") Long id) {
        PessoaDto pessoa = pessoaService.getPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @Transactional
    @PutMapping("/{pessoaId}")
    public ResponseEntity<Void> atualizarPessoa(@PathVariable("pessoaId") Long id, @RequestBody @Valid Pessoa pessoa) {
        pessoaService.atualizarPessoa(id, pessoa);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable("pessoaId") Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

}
