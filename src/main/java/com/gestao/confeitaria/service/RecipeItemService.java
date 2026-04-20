package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.FichaTecnicaResult;
import com.gestao.confeitaria.entity.*;
import com.gestao.confeitaria.repository.RecipeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.gestao.confeitaria.util.MoneyUtils.scale;

@Service
public class RecipeItemService {

    @Autowired
    private RecipeItemRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PackagingItemService packagingItemService;

    @Autowired
    private LaborService laborService;

    public RecipeItem salvar(RecipeItem item){

        Product product = productService.buscarPorId(item.getProduct().getId());
        Ingredient ingredient = ingredientService.buscarPorId(item.getIngredient().getId());

        item.setProduct(product);
        item.setIngredient(ingredient);

        return repository.save(item);
    }

    public List<RecipeItem> listar() {
        return repository.findAll();
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Item da receita não encontrado.");
        }

        repository.deleteById(id);
    }

    private BigDecimal calcularCustoIngredientes(Long productId) {
        return repository.findByProductId(productId).stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .map(RecipeItem::getCustoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularCustoEmbalagem(Long productId) {
        return packagingItemService.calcularCustoPorProduto(productId);
    }

    private BigDecimal calcularCustoMaoDeObra(Long productId) {
        Product product = productService.buscarPorId(productId);
        Labor labor = laborService.obter();

        return product.getHorasMaoDeObra().multiply(labor.getCustoHora());
    }

    public BigDecimal calcularCustoPorProduto(Long productId) {

        BigDecimal ingredientes = calcularCustoIngredientes(productId);
        BigDecimal embalagem = calcularCustoEmbalagem(productId);
        BigDecimal maoDeObra = calcularCustoMaoDeObra(productId);

        return ingredientes.add(embalagem).add(maoDeObra);
    }

    public BigDecimal calcularCustoPorRendimento(Long productId) {

        Product product = productService.buscarPorId(productId);

        BigDecimal custoTotal = calcularCustoPorProduto(productId);

        return custoTotal.divide(BigDecimal.valueOf(product.getRendimento()), 4, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularPrecoVendaTotal(Long productId) {

        Product product = productService.buscarPorId(productId);

        BigDecimal custoTotal = calcularCustoPorProduto(productId);

        return custoTotal.multiply(product.getMarkupTotal());
    }

    public BigDecimal calcularPrecoVendaPorPorcao(Long productId) {

        Product product = productService.buscarPorId(productId);

        BigDecimal custoPorPorcao = calcularCustoPorRendimento(productId);

        return custoPorPorcao.multiply(product.getMarkupRendimento());
    }

    public FichaTecnicaResult calcularFichaTecnica(Long productId) {

        BigDecimal custoIngredientes = calcularCustoIngredientes(productId);
        BigDecimal custoEmbalagem = calcularCustoEmbalagem(productId);
        BigDecimal custoMaoDeObra = calcularCustoMaoDeObra(productId);

        BigDecimal custoTotal = custoIngredientes.add(custoEmbalagem).add(custoMaoDeObra);

        Product product = productService.buscarPorId(productId);

        BigDecimal custoPorPorcao =
                productService.calcularCustoPorRendimento(product, custoTotal);

        BigDecimal precoTotal = custoTotal.multiply(product.getMarkupTotal());

        BigDecimal precoPorPorcao = custoPorPorcao.multiply(product.getMarkupRendimento());

        return FichaTecnicaResult.builder()
                .custoIngredientes(scale(custoIngredientes))
                .custoEmbalagem(scale(custoEmbalagem))
                .custoMaoDeObra(scale(custoMaoDeObra))
                .custoTotal(scale(custoTotal))
                .custoPorPorcao(scale(custoPorPorcao))
                .precoTotal(scale(precoTotal))
                .precoPorPorcao(scale(precoPorPorcao))
                .build();
    }
}
