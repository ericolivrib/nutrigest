package br.ufsm.redescomp.nutrigest.util;

import br.ufsm.redescomp.nutrigest.dto.PessoaRequest;
import br.ufsm.redescomp.nutrigest.dto.PessoaResponse;
import br.ufsm.redescomp.nutrigest.model.Pessoa;

public final class PessoaEntityMapper {

    private PessoaEntityMapper() {
    }

    public static Pessoa mapToEntity(PessoaRequest dto) {
        return Pessoa.builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .dataNascimento(dto.dataNascimento())
                .genero(dto.genero())
                .altura(dto.altura())
                .peso(dto.peso())
                .nivelAtividade(dto.nivelAtividade())
                .objetivo(dto.objetivo())
                .build();
    }

    public static PessoaResponse mapFromEntity(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getTelefone(),
                pessoa.getObjetivo(),
                pessoa.getAltura(),
                pessoa.getPeso(),
                pessoa.getDataNascimento(),
                pessoa.getGenero(),
                pessoa.getNivelAtividade()
        );
    }

}
