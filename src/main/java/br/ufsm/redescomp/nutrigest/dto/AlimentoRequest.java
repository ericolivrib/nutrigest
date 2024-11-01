package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Alimento;
import br.ufsm.redescomp.nutrigest.model.CategoriaAlimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlimentoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Categoria é obrigatória")
        CategoriaAlimento categoria,

        @NotNull(message = "Porção é obrigatória")
        Integer porcao,

        @NotNull(message = "Proteínas são obrigatórias")
        Integer proteinas,

        @NotNull(message = "Calorias são obrigatórias")
        Integer calorias,

        @NotNull(message = "Gorduras são obrigatórias")
        Integer gorduras,

        @NotNull(message = "Carboidratos são obrigatórios")
        Integer carboidratos,

        @NotBlank(message = "Vitaminas são obrigatórias")
        String vitaminas,

        @NotBlank(message = "Minerais são obrigatórios")
        String minerais,

        @NotNull(message = "Índice glicêmico é obrigatória")
        Integer indiceGlicemico
) {

    public Alimento mapToEntity() {
        return Alimento.builder()
                .nome(nome)
                .categoria(categoria)
                .porcao(porcao)
                .proteinas(proteinas)
                .calorias(calorias)
                .gorduras(gorduras)
                .carboidratos(carboidratos)
                .vitaminas(vitaminas)
                .minerais(minerais)
                .indiceGlicemico(indiceGlicemico)
                .build();
    }

}
