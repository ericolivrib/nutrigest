package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Alimento;
import br.ufsm.redescomp.nutrigest.model.CategoriaAlimento;

public record AlimentoResponse(
        Long id,
        String nome,
        CategoriaAlimento categoria,
        Integer porcao,
        Integer proteinas,
        Integer calorias,
        Integer gorduras,
        Integer carboidratos,
        String vitaminas,
        String minerais,
        Integer indiceGlicemico
) {

    public static AlimentoResponse mapFromEntity(Alimento alimento) {
        return new AlimentoResponse(
                alimento.getId(),
                alimento.getNome(),
                alimento.getCategoria(),
                alimento.getPorcao(),
                alimento.getProteinas(),
                alimento.getCalorias(),
                alimento.getGorduras(),
                alimento.getCarboidratos(),
                alimento.getVitaminas(),
                alimento.getMinerais(),
                alimento.getIndiceGlicemico()
        );
    }

}
