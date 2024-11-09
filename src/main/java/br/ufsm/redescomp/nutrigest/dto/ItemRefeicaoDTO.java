package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.ItemRefeicao;

public record ItemRefeicaoDTO(
        AlimentoDto alimento,
        Integer quantidade,
        Integer calorias,
        Integer carboidratos,
        Integer proteinas,
        Integer gorduras
) {

    public ItemRefeicaoDTO(ItemRefeicao item) {
        this(new AlimentoDto(item.getAlimento()), item.getQuantidade(), item.getCalorias(), item.getCarboidratos(),
                item.getProteinas(), item.getGorduras());
    }

}
