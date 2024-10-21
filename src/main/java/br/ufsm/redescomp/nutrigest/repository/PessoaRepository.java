package br.ufsm.redescomp.nutrigest.repository;

import br.ufsm.redescomp.nutrigest.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
