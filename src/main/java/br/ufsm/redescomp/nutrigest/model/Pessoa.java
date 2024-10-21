package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
    private List<Preferencia> preferencias;

    @OneToMany(mappedBy = "pessoa")
    private List<Preferencia> historicosSaude;

    @OneToMany(mappedBy = "pessoa")
    private List<Refeicao> refeicoes;

}
