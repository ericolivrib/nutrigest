package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Alimento;
import br.ufsm.redescomp.nutrigest.model.ItemRefeicao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemRefeicaoRequest(
        @NotNull(message = "ID do alimento é obrigatório")
        Long alimentoId,

        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 1, message = "Deve haver no mínimo 1 quantidade deste alimento")
        Integer quantidade,

        @NotNull(message = "Quantidade de calorias do alimento são obrigatórias")
        Integer calorias,

        @NotNull(message = "Quantidade de carboidratos do alimento são obrigatórias")
        Integer carboidratos,

        @NotNull(message = "Quantidade de proteínas do alimento são obrigatórias")
        Integer proteinas,

        @NotNull(message = "Quantidade de gorduras do alimento são obrigatórias")
        Integer gorduras
) {

    public ItemRefeicao mapToEntity() {
        return ItemRefeicao.builder()
                .alimento(Alimento.builder().id(alimentoId).build())
                .quantidade(quantidade)
                .calorias(calorias)
                .carboidratos(carboidratos)
                .proteinas(proteinas)
                .gorduras(gorduras)
                .build();
    }

}
