package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.ProductCategory;
import com.gestao.confeitaria.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    public List<ProductCategory> listar() {
        return repository.findByAtivoTrue();
    }

    public ProductCategory buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public ProductCategory salvar(ProductCategory category) {
        return repository.save(category);
    }

    public ProductCategory alterar(Long id, ProductCategory atualizado) {
        ProductCategory existente = buscarPorId(id);
        existente.setNome(atualizado.getNome());
        existente.setAtivo(atualizado.isAtivo());
        return repository.save(existente);
    }

    public void delete(Long id) {
        ProductCategory existente = buscarPorId(id);
        existente.setAtivo(false); // soft delete
        repository.save(existente);
    }
}