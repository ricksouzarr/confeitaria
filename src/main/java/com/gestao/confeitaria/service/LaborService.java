package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Labor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LaborService {

    private Labor labor;

    public Labor salvar(Labor l) {
        this.labor = l;
        return labor;
    }

    public Labor obter() {
        if (labor == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mão de obra não configurada"
            );
        }
        return labor;
    }
}