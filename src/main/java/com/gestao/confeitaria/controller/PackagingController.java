package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.service.PackagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}