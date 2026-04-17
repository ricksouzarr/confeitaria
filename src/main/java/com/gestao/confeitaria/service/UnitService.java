package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Unit;
import com.gestao.confeitaria.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    public Unit buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada"));
    }
    public Unit salvar(Unit unit){
        return repository.save(unit);
    }
    public List<Unit> listar(){
        return repository.findAll();
    }
}