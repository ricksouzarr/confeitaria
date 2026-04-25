package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Ingredient;
import com.gestao.confeitaria.service.IngredientService;
import com.gestao.confeitaria.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(
            @PathVariable Long id,
            @RequestBody Ingredient request
    ) {
        return ResponseEntity.ok(service.alterar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
