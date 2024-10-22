package br.ufsm.redescomp.nutrigest.repository;

import br.ufsm.redescomp.nutrigest.model.PreferenciaAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaAlimentarRepository extends JpaRepository<PreferenciaAlimentar, Long> {
}
