package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Ingredient;
import com.gestao.confeitaria.service.IngredientService;
import com.gestao.confeitaria.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService service;

    @Autowired
    private UnitService unitService;

    @PostMapping
    public Ingredient criar(@RequestBody Ingredient ingredient){
        return service.salvar(ingredient);
    }

    @GetMapping
    public List<Ingredient> listar(){
        return service.listar();
    }
}
