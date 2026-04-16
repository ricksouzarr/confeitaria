package com.gestao.confeitaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    //Quantas unidades esse produto gera (ex: 6 fatias)
    private Integer rendimento;

    //Multiplicador de preço (ex.: 1.5)
    //private Double markup;

    private BigDecimal markupTotal;
    private BigDecimal markupRendimento;

    // Quanto tempo demora para fabricação do produto em horas;
    private BigDecimal horasMaoDeObra;

}
