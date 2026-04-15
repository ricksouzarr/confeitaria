package com.gestao.confeitaria.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Labor {

    private Long id;

    // custo por hora
    private Double custoHora;
}