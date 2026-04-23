package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.entity.PackagingItem;
import com.gestao.confeitaria.service.PackagingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/packaging-items")
public class PackagingItemController {

    @Autowired
    private PackagingItemService service;

    @PostMapping
    public PackagingItem criar(@RequestBody PackagingItem item) {
        return service.salvar(item);
    }

    @GetMapping
    public List<PackagingItem> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackagingItem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}