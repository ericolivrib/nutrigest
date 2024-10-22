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
@Table(name = "preferencias")
public class PreferenciaAlimentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preferencia_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private TipoAlimentar tipoAlimentar;

    @Column(name = "intolerante_gluten")
    private boolean intoleranteLactose;

    @Column(name = "intolerante_lactose")
    private boolean intoleranteGluten;

    @Column(name = "outras_restricoes")
    private String outrasRestricoes;

}
