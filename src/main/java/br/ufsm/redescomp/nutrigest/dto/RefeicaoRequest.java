package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.PeriodoRefeicao;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.model.Refeicao;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RefeicaoRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        Long pessoaId,

        @NotNull(message = "Período é obrigatório")
        PeriodoRefeicao periodo,

        @NotNull(message = "Data é obrigatória")
        LocalDate data,

        @NotNull(message = "Proteínas totais são obrigatórias")
        Integer proteinasTotais,

        @NotNull(message = "Calorias totais são obrigatórias")
        Integer caloriasTotais,

        @NotNull(message = "Gorduras totais são obrigatórias")
        Integer gordurasTotais,

        @NotNull(message = "Carboidratos totais são obrigatórias")
        Integer carboidratosTotais
) {

    public Refeicao mapToEntity() {
        return Refeicao.builder()
                .pessoa(Pessoa.builder().id(pessoaId).build())
                .periodo(periodo)
                .data(data)
                .proteinasTotais(proteinasTotais)
                .caloriasTotais(caloriasTotais)
                .gordurasTotais(gordurasTotais)
                .carboidratosTotais(carboidratosTotais)
                .build();
    }
}
