package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.service.PackagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/packagings")
public class PackagingController {

    @Autowired
    private PackagingService service;

    @PostMapping
    public Packaging criar(@RequestBody Packaging embalagem) {
        return service.salvar(embalagem);
    }

    @GetMapping
    public List<Packaging> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Packaging> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}