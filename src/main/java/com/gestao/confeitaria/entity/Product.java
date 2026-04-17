package com.gestao.confeitaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.validation.constraints.*;

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

    @NotBlank
    private String nome;

    //Quantas unidades esse produto gera (ex: 6 fatias)
    @NotNull
    @Min(1)
    private Integer rendimento;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal markupTotal;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal markupRendimento;

    // Quanto tempo demora para fabricação do produto em horas;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal horasMaoDeObra;

}
