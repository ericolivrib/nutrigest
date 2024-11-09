package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.PessoaDTO;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Transactional
    @PostMapping("/pessoas")
    public ResponseEntity<Void> adicionarPessoa(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        pessoaService.adicionarPessoa(pessoa);
        var location = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaDTO>> getPessoas() {
        List<PessoaDTO> pessoas = pessoaService.getPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/pessoas/{pessoaId}")
    public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable("pessoaId") Long id) {
        PessoaDTO pessoa = pessoaService.getPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/usuarios/{usuarioId}/pessoas")
    public ResponseEntity<PessoaDTO> getPessoaByUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        PessoaDTO pessoa = pessoaService.getPessoaByUsuario(usuarioId);
        return ResponseEntity.ok(pessoa);
    }

    @Transactional
    @PutMapping("/pessoas/{pessoaId}")
    public ResponseEntity<Void> atualizarPessoa(@PathVariable("pessoaId") Long id, @RequestBody @Valid Pessoa pessoa) {
        pessoaService.atualizarPessoa(id, pessoa);
        return ResponseEntity.noContent().build();
    }

}
