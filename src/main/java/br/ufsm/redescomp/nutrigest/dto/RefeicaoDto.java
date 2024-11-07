package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.PeriodoRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;

import java.time.LocalDate;

public record RefeicaoDto(
        PeriodoRefeicao periodo,
        LocalDate data,
        Integer proteinasTotais,
        Integer caloriasTotais,
        Integer gordurasTotais,
        Integer carboidratosTotais
) {

    public RefeicaoDto(Refeicao refeicao) {
        this(refeicao.getPeriodo(), refeicao.getDataRealizacao(), refeicao.getProteinasTotais(), refeicao.getCaloriasTotais(),
                refeicao.getGordurasTotais(), refeicao.getCarboidratosTotais());
    }

}
