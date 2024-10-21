package br.ufsm.redescomp.nutrigest.repository;

import br.ufsm.redescomp.nutrigest.model.PlanoAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoAlimentarRepository extends JpaRepository<PlanoAlimentar, Long> {
}
