package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackagingService {

    private List<Packaging> embalagens = new ArrayList<>();
    private Long contador = 1L;

    @Autowired
    private UnitService unitService;

    public Packaging salvar(Packaging embalagem) {

        Unit unidade = unitService.buscarPorId(embalagem.getUnidade().getId());
        embalagem.setUnidade(unidade);

        embalagem.setId(contador++);
        embalagens.add(embalagem);

        return embalagem;
    }

    public List<Packaging> listar() {
        return embalagens;
    }

    public Packaging buscarPorId(Long id) {
        return embalagens.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Packaging não encontrada"
                ));
    }
}