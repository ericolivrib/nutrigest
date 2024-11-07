package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Genero;
import br.ufsm.redescomp.nutrigest.model.NivelAtividade;
import br.ufsm.redescomp.nutrigest.model.Pessoa;

import java.time.LocalDate;

public record PessoaDto(
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

    public PessoaDto(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getObjetivo(), pessoa.getAltura(),
                pessoa.getPeso(), pessoa.getDataNascimento(), pessoa.getGenero(), pessoa.getNivelAtividade());
    }

}
