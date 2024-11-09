package br.ufsm.redescomp.nutrigest.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PreferenciaAlimentar {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alimentar")
    private TipoAlimentar tipoAlimentar;

    @Column(name = "intolerante_gluten")
    private boolean intoleranteLactose;

    @Column(name = "intolerante_lactose")
    private boolean intoleranteGluten;

    @Column(name = "outras_restricoes")
    private String outrasRestricoes;

}
