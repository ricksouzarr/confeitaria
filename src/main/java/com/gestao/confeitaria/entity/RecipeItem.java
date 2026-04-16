package com.gestao.confeitaria.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeItem {

    private Long id;

    private Product product;

    private Ingredient ingredient;

    // quantidade usada na receita (ex: 0.2 kg)
    private BigDecimal quantidade;

    // Custo unitario total usado
    public BigDecimal getCustoTotal() {
        return quantidade.multiply(ingredient.getCustoUnitario());
    }
}