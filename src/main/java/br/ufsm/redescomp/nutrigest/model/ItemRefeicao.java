package br.ufsm.redescomp.nutrigest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
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
    @NotNull(message = "Alimento é obrigatório")
    private Alimento alimento;

    @Column(name = "quantidade")
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Deve haver no mínimo 1 quantidade deste alimento")
    private Integer quantidade;

    @Column(name = "calorias")
    @NotNull(message = "Quantidade de calorias do alimento são obrigatórias")
    private Integer calorias;

    @Column(name = "carboidratos")
    @NotNull(message = "Quantidade de carboidratos do alimento são obrigatórias")
    private Integer carboidratos;

    @Column(name = "proteinas")
    @NotNull(message = "Quantidade de proteínas do alimento são obrigatórias")
    private Integer proteinas;

    @Column(name = "gorduras")
    @NotNull(message = "Quantidade de gorduras do alimento são obrigatórias")
    private Integer gorduras;

    @PostPersist
    @PostUpdate
    @PostRemove
    public void atualizarMacronutrientesRefeicao() {
        this.refeicao.atualizarMacronutrientes();
    }
}
