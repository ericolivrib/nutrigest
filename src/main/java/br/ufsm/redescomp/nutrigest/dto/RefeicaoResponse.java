package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.PeriodoRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;

import java.time.LocalDate;

public record RefeicaoResponse(
        PeriodoRefeicao periodo,
        LocalDate data,
        Integer proteinasTotais,
        Integer caloriasTotais,
        Integer gordurasTotais,
        Integer carboidratosTotais
) {

    public static RefeicaoResponse mapFromEntity(Refeicao refeicao) {
        return new RefeicaoResponse(
                refeicao.getPeriodo(),
                refeicao.getData(),
                refeicao.getProteinasTotais(),
                refeicao.getCaloriasTotais(),
                refeicao.getGordurasTotais(),
                refeicao.getCarboidratosTotais()
        );
    }
}
