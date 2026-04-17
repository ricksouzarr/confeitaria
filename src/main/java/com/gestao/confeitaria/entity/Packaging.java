package com.gestao.confeitaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Packaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precoPacote;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidadePacote;

    @ManyToOne
    @NotNull
    private Unit unidade;

    public BigDecimal getCustoUnitario() {
        return precoPacote.divide(quantidadePacote, 4, RoundingMode.HALF_UP);
    }
}