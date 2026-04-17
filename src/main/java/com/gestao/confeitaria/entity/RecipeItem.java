package com.gestao.confeitaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecipeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Produto é obrigatório")
    private Product product;

    @ManyToOne
    @NotNull(message = "Ingrediente é obrigatório")
    private Ingredient ingredient;

    // quantidade usada na receita (ex: 0.2 kg)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidade;

    // Custo unitario total usado
    public BigDecimal getCustoTotal() {
        return quantidade.multiply(ingredient.getCustoUnitario());
    }
}