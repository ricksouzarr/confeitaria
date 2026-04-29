package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.entity.KitItem;
import com.gestao.confeitaria.service.KitItemService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kit-items")
public class KitItemController {

    @Autowired
    private KitItemService service;

    @PostMapping
    public KitItem criar(@RequestBody KitItem item) {
        return service.salvar(item);
    }

    @GetMapping("/kit/{kitId}")
    public List<KitItem> listarPorKit(@PathVariable Long kitId) {
        return service.listarPorKit(kitId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
