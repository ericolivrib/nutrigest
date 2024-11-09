package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "Endereço de e-mail inválido")
    @NotEmpty(message = "Endereço de e-mail é obrigatório")
    private String email;

    @Column(name = "senha")
    @NotEmpty(message = "Senha do usuário é obrigatória")
    @Size(min = 8, message = "Senha deve conter no mínimo 8 caracteres")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    private Permissao permissao;

    @OneToOne(mappedBy = "usuario")
    private Pessoa pessoa;

}
