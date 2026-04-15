package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private Long id;
    private String nome;

    // preço do pacote fechado (ex: 10 reais)
    private Double precoPacote;

    // quantidade do pacote (ex: 1kg, 500g, etc)
    private Double quantidadePacote;

    // ligação com Unit
    private Unit unidade;
}
