package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "progressos")
public class Progresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progresso_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "peso_atual")
    private Double pesoAtual;

    @Column(name = "percentual_gordura")
    private Integer percentualGordura;

    @Column(name = "data_medicao")
    private LocalDate dataMedicao;

}
