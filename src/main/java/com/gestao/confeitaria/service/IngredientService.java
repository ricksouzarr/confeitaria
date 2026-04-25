package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Ingredient;
import com.gestao.confeitaria.entity.Unit;
import com.gestao.confeitaria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service

public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    @Autowired
    private UnitService unitService;

    public Ingredient buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    public Ingredient salvar(Ingredient ingredient){

        Unit unidade = unitService.buscarPorId(ingredient.getUnidade().getId());

        ingredient.setUnidade(unidade);

        return repository.save(ingredient);
    }
    public List<Ingredient> listar(){
        return repository.findAll();
    }

    public void delete(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
        repository.delete(ingredient);
    }

    public Ingredient alterar(Long id, Ingredient ingredientAtualizado) {
        Ingredient existente = buscarPorId(id);

        existente.setNome(ingredientAtualizado.getNome());
        existente.setPrecoPacote(ingredientAtualizado.getPrecoPacote());
        existente.setQuantidadePacote(ingredientAtualizado.getQuantidadePacote());

        Unit unidade = unitService.buscarPorId(
                ingredientAtualizado.getUnidade().getId());
        existente.setUnidade(unidade);

        return repository.save(existente);
    }
}
