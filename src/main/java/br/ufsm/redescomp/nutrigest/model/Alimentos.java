package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alimentos")
public class Alimentos {

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

    @Column(name = "calorias")
    private Integer calorias;

    @Column(name = "carboidratos")
    private Integer carboidratos;

    @Column(name = "proteinas")
    private Integer proteinas;

    @Column(name = "gorduras")
    private Integer gorduras;

    @Column(name = "vitaminas")
    private String vitaminas;

    @Column(name = "minerais")
    private String minerais;

    @Column(name = "indice_glicemico")
    private Integer indiceGlicemico;

}
