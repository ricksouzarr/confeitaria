package com.gestao.confeitaria.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FichaTecnicaResult {

    private Double custoTotal;
    private Double custoPorPorcao;
    private Double precoTotal;
    private Double precoPorPorcao;

    private Double custoIngredientes;
    private Double custoEmbalagem;
    private Double custoMaoDeObra;

}