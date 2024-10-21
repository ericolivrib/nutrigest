package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itens_refeicoes")
public class ItemRefeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "calorias")
    private Integer calorias;

    @Column(name = "carboidratos")
    private Integer carboidratos;

    @Column(name = "proteinas")
    private Integer proteinas;

    @Column(name = "gorduras")
    private Integer gorduras;

}
