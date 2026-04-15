package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Ingredient;
import com.gestao.confeitaria.entity.Unit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service

public class IngredientService {

    private final UnitService unitService;
    private List<Ingredient> ingredientes = new ArrayList<>();
    private Long contador = 1l;

    public Ingredient buscarPorId(Long id) {
        return ingredientes.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ingredient não encontrado"
                ));
    }

    public IngredientService(UnitService unitService) {
        this.unitService = unitService;
    }

    public Ingredient salvar(Ingredient ingredient){

        // pega o id enviado
        Long unitId = ingredient.getUnidade().getId();

        //busca unidade real
        Unit unidadeCompleta = unitService.buscarPorId(unitId);

        //seta a unidade correta
        ingredient.setUnidade(unidadeCompleta);

        ingredient.setId(contador++);
        ingredientes.add(ingredient);

        return ingredient;
    }
    public List<Ingredient> listar(){
        return ingredientes;
    }
}
