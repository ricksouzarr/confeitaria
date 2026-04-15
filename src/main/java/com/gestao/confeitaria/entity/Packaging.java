package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Packaging {

    private Long id;
    private String nome;

    private Double precoPacote;
    private Double quantidadePacote;

    private Unit unidade;

    public Double getCustoUnitario() {
        return precoPacote / quantidadePacote;
    }
}