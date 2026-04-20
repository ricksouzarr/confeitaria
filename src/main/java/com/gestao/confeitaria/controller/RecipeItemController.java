package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.dto.FichaTecnicaResult;
import com.gestao.confeitaria.entity.RecipeItem;
import com.gestao.confeitaria.service.RecipeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/recipe-items")
public class RecipeItemController {

    @Autowired
    private RecipeItemService service;

    @PostMapping
    public RecipeItem criar(@RequestBody RecipeItem item) {
        return service.salvar(item);
    }

    @GetMapping
    public List<RecipeItem> listar() {
        return service.listar();
    }

    @GetMapping("/product/{id}/custo")
    public BigDecimal calcularCusto(@PathVariable Long id) {
        return service.calcularCustoPorProduto(id);
    }

    @GetMapping("/product/{id}/custo-por-porcao")
    public BigDecimal calcularCustoPorPorcao(@PathVariable Long id) {
        return service.calcularCustoPorRendimento(id);
    }

    @GetMapping("/product/{id}/preco-total")
    public BigDecimal precoTotal(@PathVariable Long id) {
        return service.calcularPrecoVendaTotal(id);
    }

    @GetMapping("/product/{id}/preco-por-porcao")
    public BigDecimal precoPorPorcao(@PathVariable Long id) {
        return service.calcularPrecoVendaPorPorcao(id);
    }

    @GetMapping("/product/{id}/ficha-tecnica")
    public FichaTecnicaResult fichaTecnica(@PathVariable Long id) {
        return service.calcularFichaTecnica(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}