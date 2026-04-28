package com.gestao.confeitaria.entity;

import jakarta.persistence.*;
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

    @Column(columnDefinition = "TEXT")
    private String observacaoFichaTecnica;

    // não cadastrável, apenas boolean
    @Column(nullable = false)
    private boolean kit = false;

    @ManyToOne
    private ProductCategory categoria;

    @ManyToOne
    private ProductType tipo;

    @ManyToOne
    private ProductOccasion ocasiao;

}
