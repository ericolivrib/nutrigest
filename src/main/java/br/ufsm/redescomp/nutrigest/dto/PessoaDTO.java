package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Genero;
import br.ufsm.redescomp.nutrigest.model.NivelAtividade;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import br.ufsm.redescomp.nutrigest.model.TipoAlimentar;

import java.time.LocalDate;

public record PessoaDTO(
        Long id,
        String nome,
        String telefone,
        String objetivo,
        Integer altura,
        Double peso,
        LocalDate dataNascimento,
        Genero genero,
        NivelAtividade nivelAtividade,
        TipoAlimentar tipoAlimentar,
        boolean intoleranteLactose,
        boolean intoleranteGluten,
        String outrasRestricoes
) {

    public PessoaDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getObjetivo(), pessoa.getAltura(),
                pessoa.getPeso(), pessoa.getDataNascimento(), pessoa.getGenero(), pessoa.getNivelAtividade(),
                pessoa.getPreferenciaAlimentar().getTipoAlimentar(),
                pessoa.getPreferenciaAlimentar().isIntoleranteLactose(),
                pessoa.getPreferenciaAlimentar().isIntoleranteGluten(),
                pessoa.getPreferenciaAlimentar().getOutrasRestricoes());
    }

}
