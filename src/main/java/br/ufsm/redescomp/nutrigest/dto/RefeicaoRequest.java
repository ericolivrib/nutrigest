package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.PeriodoRefeicao;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.model.Refeicao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RefeicaoRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        Long pessoaId,

        @NotNull(message = "Período é obrigatório")
        PeriodoRefeicao periodo,

        @NotNull(message = "Data é obrigatória")
        LocalDate data,

        @NotEmpty(message = "Itens são obrigatórios")
        List<ItemRefeicaoRequest> itens
) {

    public Refeicao mapToEntity() {
        int carboidratosTotais = itens.stream().map(ItemRefeicaoRequest::carboidratos).reduce(0, Integer::sum);
        int caloriasTotais = itens.stream().map(ItemRefeicaoRequest::calorias).reduce(0, Integer::sum);
        int proteinasTotais = itens.stream().map(ItemRefeicaoRequest::proteinas).reduce(0, Integer::sum);
        int gordurasTotais = itens.stream().map(ItemRefeicaoRequest::gorduras).reduce(0, Integer::sum);

        return Refeicao.builder()
                .pessoa(Pessoa.builder().id(pessoaId).build())
                .periodo(periodo)
                .data(data)
                .itens(itens.stream().map(ItemRefeicaoRequest::mapToEntity).toList())
                .carboidratosTotais(carboidratosTotais)
                .caloriasTotais(caloriasTotais)
                .proteinasTotais(proteinasTotais)
                .gordurasTotais(gordurasTotais)
                .build();
    }


}
