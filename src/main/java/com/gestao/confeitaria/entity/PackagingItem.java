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
public class PackagingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Produto é obrigatório")
    private Product product;

    @ManyToOne
    private Packaging packaging;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidade;

    public BigDecimal getCustoTotal() {
        return quantidade.multiply(packaging.getCustoUnitario());
    }
}