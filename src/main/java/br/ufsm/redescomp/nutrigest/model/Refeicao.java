package br.ufsm.redescomp.nutrigest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
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
    @Column(name = "periodo")
    @NotNull(message = "Período de realização da refeição é obrigatório")
    private PeriodoRefeicao periodo;

    @Column(name = "data")
    @NotNull(message = "Data de realização da refeição é obrigatória")
    private LocalDate dataRealizacao;

    @Column(name = "calorias_totais")
    private Integer caloriasTotais;

    @Column(name = "carboidratos_totais")
    private Integer carboidratosTotais;

    @Column(name = "proteinas_totais")
    private Integer proteinasTotais;

    @Column(name = "gorduras_totais")
    private Integer gordurasTotais;

    @OneToMany(mappedBy = "refeicao", fetch = FetchType.EAGER)
    @NotEmpty(message = "Itens da refeição são obrigatórios")
    private List<ItemRefeicao> itens;

    @PrePersist
    @PreUpdate
    public void atualizarMacronutrientes() {
        carboidratosTotais = itens.stream().mapToInt(ItemRefeicao::getCarboidratos).sum();
        caloriasTotais = itens.stream().mapToInt(ItemRefeicao::getCalorias).sum();
        proteinasTotais = itens.stream().mapToInt(ItemRefeicao::getProteinas).sum();
        gordurasTotais = itens.stream().mapToInt(ItemRefeicao::getGorduras).sum();
    }

}
