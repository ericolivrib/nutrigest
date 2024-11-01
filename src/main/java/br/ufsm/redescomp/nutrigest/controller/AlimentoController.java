package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.AlimentoRequest;
import br.ufsm.redescomp.nutrigest.dto.AlimentoResponse;
import br.ufsm.redescomp.nutrigest.service.AlimentoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class AlimentoController {

    private final AlimentoService alimentoService;

    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @Transactional
    @PostMapping("/alimentos")
    public ResponseEntity<Void> adicionarAlimento(@RequestBody AlimentoRequest request, UriComponentsBuilder uriBuilder) {
        Long alimentoId = alimentoService.adicionarAlimento(request);
        var uri = uriBuilder.path("/alimentos/{alimentoId}").buildAndExpand(alimentoId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/alimentos")
    public ResponseEntity<List<AlimentoResponse>> getAlimentos() {
        var alimentos = alimentoService.getAlimentos();
        return ResponseEntity.ok(alimentos);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoResponse> getAlimentoById(@PathVariable("alimentoId") Long id) {
        var alimento = alimentoService.getAlimentoById(id);
        return ResponseEntity.ok(alimento);
    }

    @PutMapping("/alimentos/{alimentoId}")
    public ResponseEntity<Void> atualizarAlimento(@PathVariable("alimentoId") Long id, @RequestBody AlimentoRequest request) {
        alimentoService.atualizarAlimento(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    public ResponseEntity<Void> deletarAlimento(@PathVariable("alimentoId") Long id) {
        alimentoService.deletarAlimento(id);
        return ResponseEntity.noContent().build();
    }

}
