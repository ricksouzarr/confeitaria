package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.Unit;
import com.gestao.confeitaria.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}