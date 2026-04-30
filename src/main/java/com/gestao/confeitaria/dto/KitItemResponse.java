package com.gestao.confeitaria.dto;

import com.gestao.confeitaria.entity.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitItemResponse {

    private Long id;
    private Product produto;
    private BigDecimal quantidade;
    private BigDecimal custoProduto;
    private BigDecimal custoTotal;
}