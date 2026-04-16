package com.gestao.confeitaria.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private Long id;
    private String nome;

    // preço do pacote fechado (ex: 10 reais)
    private BigDecimal precoPacote;

    // quantidade do pacote (ex: 1kg, 500g, etc)
    private BigDecimal quantidadePacote;

    // ligação com Unit
    private Unit unidade;

    // Custo unitario
    public BigDecimal getCustoUnitario() {
        return precoPacote.divide(quantidadePacote);
    }
}
