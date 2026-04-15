package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    private Long id;
    private String nome;

    //Quantas unidades esse produto gera (ex: 6 fatias)
    private Integer rendimento;

    //Multiplicador de preço (ex.: 1.5)
    private Double markup;

}
