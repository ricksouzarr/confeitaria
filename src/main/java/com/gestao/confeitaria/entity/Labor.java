package com.gestao.confeitaria.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Labor {

    private Long id;

    // custo por hora
    private BigDecimal custoHora;
}