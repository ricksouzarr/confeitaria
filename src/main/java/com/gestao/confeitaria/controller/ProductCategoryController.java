package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.ProductCategory;
import com.gestao.confeitaria.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping
    public List<ProductCategory> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ProductCategory criar(@RequestBody ProductCategory category) {
        return service.salvar(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(
            @PathVariable Long id,
            @RequestBody ProductCategory request
    ) {
        return ResponseEntity.ok(service.alterar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}