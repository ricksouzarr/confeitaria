package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.RecipeItem;
import com.gestao.confeitaria.service.RecipeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe-items")
public class RecipeItemController {

    @Autowired
    private RecipeItemService service;

    @PostMapping
    public RecipeItem criar(@RequestBody RecipeItem item) {
        return service.salvar(item);
    }

    @GetMapping
    public List<RecipeItem> listar() {
        return service.listar();
    }
}