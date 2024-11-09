package br.ufsm.redescomp.nutrigest.service;

import br.ufsm.redescomp.nutrigest.model.Usuario;
import br.ufsm.redescomp.nutrigest.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (Objects.isNull(usuario)) {
            throw new UsernameNotFoundException("E-mail ou senha incorretos");
        }

        return User.withUsername(email)
                .password(usuario.getSenha())
                .authorities(usuario.getPermissao().name())
                .build();
    }

}
