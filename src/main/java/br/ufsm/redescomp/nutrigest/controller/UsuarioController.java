package br.ufsm.redescomp.nutrigest.controller;

import br.ufsm.redescomp.nutrigest.model.Usuario;
import br.ufsm.redescomp.nutrigest.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        usuarioService.cadastrarUsuario(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
