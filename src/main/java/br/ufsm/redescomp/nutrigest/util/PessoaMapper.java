package br.ufsm.redescomp.nutrigest.util;

import br.ufsm.redescomp.nutrigest.dto.AdicionarPessoaRequest;
import br.ufsm.redescomp.nutrigest.dto.PessoaResponse;
import br.ufsm.redescomp.nutrigest.model.Pessoa;

public final class PessoaMapper {

    private PessoaMapper() {
    }

    public static Pessoa mapToEntity(AdicionarPessoaRequest dto) {
        return new Pessoa(
                null,
                dto.nome(),
                dto.telefone(),
                dto.dataNascimento(),
                dto.genero(),
                dto.altura(),
                dto.peso(),
                dto.nivelAtividade(),
                dto.objetivo(),
                null,
                null,
                null
        );
    }

    public static PessoaResponse mapToDto(Pessoa pessoa) {
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
