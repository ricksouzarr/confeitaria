package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.entity.PackagingItem;
import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.entity.RecipeItem;
import com.gestao.confeitaria.repository.PackagingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackagingItemService {

    @Autowired
    private PackagingItemRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PackagingService packagingService;

    public PackagingItem salvar(PackagingItem item){

        Product product = productService.buscarPorId(item.getProduct().getId());
        Packaging packaging = packagingService.findById(item.getPackaging().getId());

        item.setProduct(product);
        item.setPackaging(packaging);

        return repository.save(item);
    }

    public List<PackagingItem> listar() {
        return repository.findAll();
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Item da receita não encontrado.");
        }
        repository.deleteById(id);
    }

    public BigDecimal calcularCustoPorProduto(Long productId) {

        return repository.findByProductId(productId).stream()
                .map(PackagingItem::getCustoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public PackagingItem findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Embalagem não encontrada"));
    }


}
