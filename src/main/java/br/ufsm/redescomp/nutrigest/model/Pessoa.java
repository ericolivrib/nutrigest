package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
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

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Genero genero;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "peso")
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_atividade_fisica")
    private NivelAtividade nivelAtividade;

    @Column(name = "objetivo")
    private String objetivo;

    @OneToMany(mappedBy = "pessoa")
    private List<PreferenciaAlimentar> preferenciasAlimentares;

    @OneToMany(mappedBy = "pessoa")
    private List<PlanoAlimentar> planosAlimentares;

    @OneToMany(mappedBy = "pessoa")
    private List<HistoricoSaude> historicosSaude;

    @OneToMany(mappedBy = "pessoa")
    private List<Refeicao> refeicoes;

    @OneToMany(mappedBy = "pessoa")
    private List<AtividadeFisica> atividadesFisicas;

    @OneToMany(mappedBy = "pessoa")
    private List<Progresso> progressos;

}
