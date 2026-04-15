package com.gestao.confeitaria.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

    private Long id;
    private String nome;
    private String sigla;
    private UnitType tipo;

}