package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.entity.Unit;
import com.gestao.confeitaria.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackagingService {

    @Autowired
    private PackagingRepository repository;

    @Autowired
    private UnitService unitService;

    public Packaging salvar(Packaging embalagem){

        Unit unidade = unitService.buscarPorId(embalagem.getUnidade().getId());

        embalagem.setUnidade(unidade);

        return repository.save(embalagem);
    }

    public List<Packaging> listar() {
        return repository.findAll();
    }

    public Packaging findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Embalagem não encontrada"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Embalagem não encontrada.");
        }
        repository.deleteById(id);
    }
}