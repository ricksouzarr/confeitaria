package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.ProductType;
import com.gestao.confeitaria.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {

    @Autowired
    private ProductTypeService service;

    @GetMapping
    public List<ProductType> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ProductType criar(@RequestBody ProductType type) {
        return service.salvar(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> update(
            @PathVariable Long id,
            @RequestBody ProductType request
    ) {
        return ResponseEntity.ok(service.alterar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}