package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.KitItemResponse;
import com.gestao.confeitaria.entity.KitItem;
import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.repository.KitItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.gestao.confeitaria.util.MoneyUtils.scale;


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
                    BigDecimal custoFichaProduto = recipeItemService
                            .calcularFichaTecnica(item.getProduto().getId())
                            .getCustoTotal();

                    BigDecimal rendimento = BigDecimal.valueOf(item.getProduto().getRendimento());

                    BigDecimal custoUnitario = custoFichaProduto.divide(rendimento, 2, RoundingMode.HALF_UP);

                    return item.getQuantidade().multiply(custoUnitario);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<KitItemResponse> listarPorKitComCustos(Long kitId) {
        return repository.findByKitId(kitId).stream()
                .map(item -> {
                    BigDecimal custoFichaProduto = recipeItemService
                            .calcularFichaTecnica(item.getProduto().getId())
                            .getCustoTotal();

                    BigDecimal rendimento = BigDecimal.valueOf(item.getProduto().getRendimento());

                    BigDecimal custoProduto = custoFichaProduto.divide(rendimento, 2, RoundingMode.HALF_UP);

                    BigDecimal custoTotal = custoProduto.multiply(item.getQuantidade());

                    return KitItemResponse.builder()
                            .id(item.getId())
                            .produto(item.getProduto())
                            .quantidade(item.getQuantidade())
                            .custoProduto(scale(custoProduto))
                            .custoTotal(scale(custoTotal))
                            .build();
                })
                .toList();
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
