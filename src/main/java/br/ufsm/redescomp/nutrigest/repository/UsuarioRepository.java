package br.ufsm.redescomp.nutrigest.repository;

import br.ufsm.redescomp.nutrigest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
