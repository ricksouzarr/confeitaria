package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Labor;
import com.gestao.confeitaria.service.LaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/labor")
public class LaborController {

    @Autowired
    private LaborService service;

    @PostMapping
    public Labor salvar(@RequestBody Labor labor) {
        return service.salvar(labor);
    }

    @GetMapping
    public Labor obter() {
        return service.obter();
    }
}