package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Unit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {


    private List<Unit> unidades = new ArrayList<>();
    private Long contador = 1L;

    public Unit buscarPorId(Long id) {
        return unidades.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unit não encontrada"
                ));
    }

    public Unit salvar(Unit unit) {
        unit.setId(contador++);
        unidades.add(unit);
        return unit;
    }

    public List<Unit> listar() {
        return unidades;
    }
}