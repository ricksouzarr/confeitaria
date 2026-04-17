package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Labor;
import com.gestao.confeitaria.repository.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LaborService {

    @Autowired
    private LaborRepository repository;


    public Labor salvar(Labor labor) {

        List<Labor> lista = repository.findAll();

        if (!lista.isEmpty()) {
            // já existe → atualiza
            Labor existente = lista.get(0);
            existente.setCustoHora(labor.getCustoHora());
            return repository.save(existente);
        }

        // não existe → cria
        return repository.save(labor);
    }

    public Labor obter() {
        return repository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Mão de obra não configurada"
                ));
    }
}