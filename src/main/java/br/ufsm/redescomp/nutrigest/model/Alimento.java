package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alimentos")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alimento_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaAlimento categoria;

    @Column(name = "porcao")
    private Integer porcao;

    // FIXME: Alterar tipo para Double
    @Column(name = "calorias")
    private Integer calorias;

    @Column(name = "carboidratos")
    private Integer carboidratos;

    @Column(name = "proteinas")
    private Integer proteinas;

    // FIXME: Alterar nome para percentualGordura
    @Column(name = "gorduras")
    private Integer gorduras;

    // FIXME: Alterar tipo para List<String> (DTOs)
    @Column(name = "vitaminas")
    private String vitaminas;

    // FIXME: Alterar tipo para List<String> (DTOs)
    @Column(name = "minerais")
    private String minerais;

    @Column(name = "indice_glicemico")
    private Integer indiceGlicemico;

    @OneToMany(mappedBy = "alimento")
    private List<ItemRefeicao> itensRefeicao;

}
