package com.gestao.confeitaria.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackagingItem {

    private Long id;

    private Product product;
    private Packaging packaging;

    private BigDecimal quantidade;

    public BigDecimal getCustoTotal() {
        return quantidade.multiply(packaging.getCustoUnitario());
    }
}