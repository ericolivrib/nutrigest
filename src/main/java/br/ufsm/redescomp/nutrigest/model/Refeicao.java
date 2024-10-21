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
@Table(name = "refeicoes")
public class Refeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refeicao_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario")
    private HorarioRefeicao horario;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "calorias_totais")
    private Integer caloriasTotais;

    @Column(name = "carboidratos_totais")
    private Integer carboidratosTotais;

    @Column(name = "proteinas_totais")
    private Integer proteinasTotais;

    @Column(name = "gorduras_totais")
    private Integer gordurasTotais;

    @OneToMany(mappedBy = "refeicao")
    private List<ItemRefeicao> itens;

}
