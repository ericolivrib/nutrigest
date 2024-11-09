package br.ufsm.redescomp.nutrigest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "telefone")
    @Pattern(regexp = "\\d{2}\\s\\d{5}-\\d{4}", message = "Formato de telefone inválido")
    private String telefone;

    @Column(name = "data_nascimento")
    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    @NotNull(message = "Gênero é obrigatório")
    private Genero genero;

    @Column(name = "altura")
    @Min(value = 50, message = "Altura abaixo do valor mínimo")
    @Max(value = 300, message = "Altura acima do valor máximo")
    @NotNull(message = "Altura é obrigatória")
    private Integer altura;

    @Column(name = "peso")
    @Min(value = 1, message = "Peso abaixo do valor mínimo")
    @Max(value = 600, message = "Peso acima do valor máximo")
    @NotNull(message = "Peso é obrigatório")
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_atividade_fisica")
    @NotNull(message = "Nível de atividade é obrigatório")
    private NivelAtividade nivelAtividade;

    @Column(name = "objetivo")
    @NotBlank(message = "Objetivo é obrigatório")
    private String objetivo;

    @Embedded
    private PreferenciaAlimentar preferenciaAlimentar;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<PlanoAlimentar> planosAlimentares;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Refeicao> refeicoes;

}
