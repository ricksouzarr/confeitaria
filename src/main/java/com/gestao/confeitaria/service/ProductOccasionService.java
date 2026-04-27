package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.ProductOccasion;
import com.gestao.confeitaria.repository.ProductOccasionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductOccasionService {

    @Autowired
    private ProductOccasionRepository repository;

    public List<ProductOccasion> listar() {
        return repository.findByAtivoTrue();
    }

    public ProductOccasion buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ocasião não encontrada"));
    }

    public ProductOccasion salvar(ProductOccasion occasion) {
        return repository.save(occasion);
    }

    public ProductOccasion alterar(Long id, ProductOccasion atualizado) {
        ProductOccasion existente = buscarPorId(id);
        existente.setNome(atualizado.getNome());
        existente.setAtivo(atualizado.isAtivo());
        return repository.save(existente);
    }

    public void delete(Long id) {
        ProductOccasion existente = buscarPorId(id);
        existente.setAtivo(false);
        repository.save(existente);
    }
}