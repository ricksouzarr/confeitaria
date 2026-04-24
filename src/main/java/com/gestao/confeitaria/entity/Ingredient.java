package com.gestao.confeitaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    // preço do pacote fechado (ex: 10 reais)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precoPacote;

    // quantidade do pacote (ex: 1kg, 500g, etc)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidadePacote;

    // ligação com Unit
    @ManyToOne
    @NotNull(message = "unidade de medida é obrigatório")
    private Unit unidade;

    // Custo unitario
    public BigDecimal getCustoUnitario() {
        return precoPacote.divide(quantidadePacote, 4, RoundingMode.HALF_UP);
    }
}
