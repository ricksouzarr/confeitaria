package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.ProductOccasion;
import com.gestao.confeitaria.service.ProductOccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product-occasions")
public class ProductOccasionController {

    @Autowired
    private ProductOccasionService service;

    @GetMapping
    public List<ProductOccasion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOccasion> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ProductOccasion criar(@RequestBody ProductOccasion occasion) {
        return service.salvar(occasion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOccasion> update(
            @PathVariable Long id,
            @RequestBody ProductOccasion request
    ) {
        return ResponseEntity.ok(service.alterar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}