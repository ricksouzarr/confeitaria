package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeItem {

    private Long id;

    private Product product;

    private Ingredient ingredient;

    // quantidade usada na receita (ex: 0.2 kg)
    private Double quantidade;
}