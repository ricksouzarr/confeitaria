package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.KitItem;
import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.repository.KitItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KitItemService {

    @Autowired
    private KitItemRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    @Lazy
    private RecipeItemService recipeItemService;

    public KitItem salvar(KitItem item) {
        Product kit = productService.buscarPorId(
                item.getKit().getId());
        Product filho = productService.buscarPorId(
                item.getProduto().getId());

        if (!kit.isKit()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Produto não é um kit");
        }
        if (filho.isKit()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é permitido adicionar um kit dentro de outro kit");
        }

        item.setKit(kit);
        item.setProduto(filho);
        return repository.save(item);
    }

    public BigDecimal calcularCustoPorKit(Long kitId) {
        return repository.findByKitId(kitId).stream()
                .map(item -> {
                    // custo total do filho × quantidade usada
                    BigDecimal custoFilho = recipeItemService
                            .calcularFichaTecnica(item.getProduto().getId())
                            .getCustoTotal();
                    return item.getQuantidade().multiply(custoFilho);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<KitItem> listarPorKit(Long kitId) {
        return repository.findByKitId(kitId);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Item não encontrado");
        repository.deleteById(id);
    }
}
