package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.ProductType;
import com.gestao.confeitaria.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository repository;

    public List<ProductType> listar() {
        return repository.findByAtivoTrue();
    }

    public ProductType buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo não encontrado"));
    }

    public ProductType salvar(ProductType type) {
        return repository.save(type);
    }

    public ProductType alterar(Long id, ProductType atualizado) {
        ProductType existente = buscarPorId(id);
        existente.setNome(atualizado.getNome());
        existente.setAtivo(atualizado.isAtivo());
        return repository.save(existente);
    }

    public void delete(Long id) {
        ProductType existente = buscarPorId(id);
        existente.setAtivo(false);
        repository.save(existente);
    }
}