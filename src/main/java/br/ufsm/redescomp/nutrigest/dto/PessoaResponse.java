package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Genero;
import br.ufsm.redescomp.nutrigest.model.NivelAtividade;

import java.time.LocalDate;

public record PessoaResponse(
        Long id,
        String nome,
        String telefone,
        String objetivo,
        Integer altura,
        Double peso,
        LocalDate dataNascimento,
        Genero genero,
        NivelAtividade nivelAtividade
) {
}
