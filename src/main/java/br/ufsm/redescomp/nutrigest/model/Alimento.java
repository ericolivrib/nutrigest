package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    @NotNull(message = "Categoria do alimento é obrigatória")
    private CategoriaAlimento categoria;

    @Column(name = "porcao")
    @NotNull(message = "Porção do alimento é obrigatória")
    @Min(value = 1, message = "Porção do alimento deve ser de no mínimo 1")
    private Integer porcao;

    // FIXME: Alterar tipo para Double
    @Column(name = "calorias")
    @NotNull(message = "Calorias são obrigatórias")
    private Integer calorias;

    @Column(name = "carboidratos")
    @NotNull(message = "Carboidratos são obrigatórios")
    private Integer carboidratos;

    @Column(name = "proteinas")
    @NotNull(message = "Proteínas são obrigatórias")
    private Integer proteinas;

    // FIXME: Alterar nome para percentualGordura
    @Column(name = "gorduras")
    @NotNull(message = "Gorduras são obrigatórias")
    private Integer gorduras;

    // FIXME: Alterar tipo para List<String> (DTOs)
    @Column(name = "vitaminas")
    @NotBlank(message = "Vitaminas são obrigatórias")
    private String vitaminas;

    // FIXME: Alterar tipo para List<String> (DTOs)
    @Column(name = "minerais")
    @NotBlank(message = "Minerais são obrigatórios")
    private String minerais;

    @Column(name = "indice_glicemico")
    @NotNull(message = "Índice glicêmico é obrigatória")
    private Integer indiceGlicemico;

    @OneToMany(mappedBy = "alimento")
    private List<ItemRefeicao> itensRefeicao;

}
