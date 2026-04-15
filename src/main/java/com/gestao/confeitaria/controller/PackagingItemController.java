package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.PackagingItem;
import com.gestao.confeitaria.service.PackagingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}