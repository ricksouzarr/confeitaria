package com.gestao.confeitaria.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FichaTecnicaResult {

    private BigDecimal custoTotal;
    private BigDecimal custoPorPorcao;
    private BigDecimal precoTotal;
    private BigDecimal precoPorPorcao;

    private BigDecimal custoIngredientes;
    private BigDecimal custoEmbalagem;
    private BigDecimal custoMaoDeObra;

}