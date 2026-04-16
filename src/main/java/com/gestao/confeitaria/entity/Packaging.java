package com.gestao.confeitaria.entity;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Packaging {

    private Long id;
    private String nome;

    private BigDecimal precoPacote;
    private BigDecimal quantidadePacote;

    private Unit unidade;

    public BigDecimal getCustoUnitario() {
        return precoPacote.divide(quantidadePacote, 4, RoundingMode.HALF_UP);
    }
}