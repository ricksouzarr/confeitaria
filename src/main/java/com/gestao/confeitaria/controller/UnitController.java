package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.entity.Unit;
import com.gestao.confeitaria.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitService service;

    @PostMapping
    public Unit criar(@RequestBody Unit unit) {
        return service.salvar(unit);
    }

    @GetMapping
    public List<Unit> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Unit> update(
            @PathVariable Long id,
            @RequestBody @Valid Unit request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Unit> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}