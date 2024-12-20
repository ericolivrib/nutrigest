package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.model.Permissao;
import br.ufsm.redescomp.nutrigest.model.Usuario;
import br.ufsm.redescomp.nutrigest.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(Usuario.builder().email(usuario.getEmail())
                .senha(passwordEncoder.encode(usuario.getSenha()))
                        .permissao(Permissao.ROLE_NUTRICIONISTA)
                .build());
    }

}
