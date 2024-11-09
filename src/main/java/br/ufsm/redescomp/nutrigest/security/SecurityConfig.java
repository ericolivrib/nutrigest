package br.ufsm.redescomp.nutrigest.security;

import br.ufsm.redescomp.nutrigest.model.Permissao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    public SecurityConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth").permitAll()

                        /// Usuários
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/usuarios").permitAll()

                        /// Pessoas
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/pessoas").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/pessoas").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/pessoas/{pessoaId}").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/usuarios/{usuarioId}/pessoas").hasAnyAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.PUT, "/nutrigest/api/pessoas/{pessoaId}").hasAuthority("ROLE_NUTRICIONISTA")

                        /// Alimentos
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/alimentos").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/alimentos").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/alimentos/{alimentoId}").hasAuthority("ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.PUT, "/nutrigest/api/alimentos/{alimentoId}").hasAuthority("ROLE_NUTRICIONISTA")

                        /// Refeições
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/refeicoes").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/refeicoes/{refeicaoId}").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.PUT, "/nutrigest/api/refeicoes/{refeicaoId}").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.DELETE, "/nutrigest/api/refeicoes/{refeicaoId}").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.GET, "/nutrigest/api/pessoas/{pessoaId}/refeicoes").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")

                        /// Itens de refeições
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/refeicoes/{refeicaoId}/itens").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")
                        .requestMatchers(HttpMethod.POST, "/nutrigest/api/itens/{itemId}").hasAnyAuthority("ROLE_PACIENTE", "ROLE_NUTRICIONISTA")

                        .anyRequest().authenticated())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
