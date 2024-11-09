package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.dto.AlimentoDto;
import br.ufsm.redescomp.nutrigest.model.Alimento;
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
    public ResponseEntity<Void> adicionarAlimento(@RequestBody Alimento alimento, UriComponentsBuilder uriBuilder) {
        alimentoService.adicionarAlimento(alimento);
        var uri = uriBuilder.path("/alimentos/{alimentoId}").buildAndExpand(alimento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/alimentos")
    public ResponseEntity<List<AlimentoDto>> getAlimentos() {
        var alimentos = alimentoService.getAlimentos();
        return ResponseEntity.ok(alimentos);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoDto> getAlimentoById(@PathVariable("alimentoId") Long id) {
        var alimento = alimentoService.getAlimentoById(id);
        return ResponseEntity.ok(alimento);
    }

    @PutMapping("/alimentos/{alimentoId}")
    public ResponseEntity<Void> atualizarAlimento(@PathVariable("alimentoId") Long id, @RequestBody Alimento alimento) {
        alimentoService.atualizarAlimento(id, alimento);
        return ResponseEntity.noContent().build();
    }

}
