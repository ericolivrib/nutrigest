package br.ufsm.redescomp.nutrigest.dto;

import br.ufsm.redescomp.nutrigest.model.Genero;
import br.ufsm.redescomp.nutrigest.model.NivelAtividade;
import br.ufsm.redescomp.nutrigest.model.Pessoa;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PessoaRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Pattern(regexp = "\\d{2}\\s\\d{5}-\\d{4}", message = "Formato de telefone inválido")
        String telefone,

        @NotBlank(message = "Objetivo é obrigatório")
        String objetivo,

        @Min(value = 50, message = "Altura abaixo do valor mínimo")
        @Max(value = 300, message = "Altura acima do valor máximo")
        @NotNull(message = "Altura é obrigatória")
        Integer altura,

        @Min(value = 1, message = "Peso abaixo do valor mínimo")
        @Max(value = 600, message = "Peso acima do valor máximo")
        @NotNull(message = "Peso é obrigatório")
        Double peso,

        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @NotNull(message = "Gênero é obrigatório")
        Genero genero,

        @NotNull(message = "Nível de atividade é obrigatório")
        NivelAtividade nivelAtividade
) {
        
        public Pessoa mapToEntity() {
                return Pessoa.builder()
                        .nome(this.nome())
                        .telefone(this.telefone())
                        .dataNascimento(this.dataNascimento())
                        .genero(this.genero())
                        .altura(this.altura())
                        .peso(this.peso())
                        .nivelAtividade(this.nivelAtividade())
                        .objetivo(this.objetivo())
                        .build();
        }
}
