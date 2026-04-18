package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.service.ProductService;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product criar(@RequestBody @Valid Product product) {
        return service.salvar(product);
    }

    @GetMapping
    public List<Product> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}