package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Packaging;
import com.gestao.confeitaria.entity.PackagingItem;
import com.gestao.confeitaria.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackagingItemService {

    private List<PackagingItem> itens = new ArrayList<>();
    private Long contador = 1L;

    @Autowired
    private ProductService productService;

    @Autowired
    private PackagingService packagingService;

    public PackagingItem salvar(PackagingItem item) {

        Product product = productService.buscarPorId(item.getProduct().getId());
        Packaging packaging = packagingService.buscarPorId(item.getPackaging().getId());

        item.setProduct(product);
        item.setPackaging(packaging);

        item.setId(contador++);
        itens.add(item);

        return item;
    }

    public List<PackagingItem> listar() {
        return itens;
    }

    public BigDecimal calcularCustoPorProduto(Long productId) {

        return itens.stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .map(PackagingItem::getCustoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
