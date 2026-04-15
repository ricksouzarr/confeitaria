package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> produtos = new ArrayList<>();
    private Long contador = 1L;

    public Product buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product não encontrado"
                ));
    }

    public Product salvar(Product product){
        product.setId(contador++);
        produtos.add(product);
        return product;
    }

    public List<Product> listar() {
        return produtos;
    }
}
