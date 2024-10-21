package br.ufsm.redescomp.nutrigest.repository;

import br.ufsm.redescomp.nutrigest.model.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressoRepository extends JpaRepository<Progresso, Long> {
}
