package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackagingItem {

    private Long id;

    private Product product;
    private Packaging packaging;

    private Double quantidade;

    public Double getCustoTotal() {
        return quantidade * packaging.getCustoUnitario();
    }
}