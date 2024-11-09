package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.PeriodoRefeicao;
import br.ufsm.redescomp.nutrigest.model.Refeicao;

import java.time.LocalDate;
import java.util.List;

public record RefeicaoDTO(
        Long id,
        Long pessoaId,
        PeriodoRefeicao periodo,
        LocalDate dataRealizacao,
        Integer caloriasTotais,
        Integer carboidratosTotais,
        Integer proteinasTotais,
        Integer gordurasTotais,
        List<ItemRefeicaoDTO> itens
) {

    public RefeicaoDTO(Refeicao refeicao) {
        this(refeicao.getId(), refeicao.getPessoa().getId(), refeicao.getPeriodo(), refeicao.getDataRealizacao(),
                refeicao.getCaloriasTotais(), refeicao.getCarboidratosTotais(), refeicao.getProteinasTotais(),
                refeicao.getGordurasTotais(), refeicao.getItens().stream().map(ItemRefeicaoDTO::new).toList());
    }

}
