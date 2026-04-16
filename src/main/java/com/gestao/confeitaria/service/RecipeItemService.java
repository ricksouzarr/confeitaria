package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.FichaTecnicaResult;
import com.gestao.confeitaria.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.gestao.confeitaria.util.MoneyUtils.scale;

@Service
public class RecipeItemService {

    private List<RecipeItem> itens = new ArrayList<>();
    private Long contador = 1L;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PackagingItemService packagingItemService;

    @Autowired
    private LaborService laborService;

    public RecipeItem salvar(RecipeItem item){

        //buscar product real
        Product product = productService.buscarPorId(item.getProduct().getId());

        //buscar ingredient real
        Ingredient ingredient = ingredientService.buscarPorId(item.getIngredient().getId());

        item.setProduct(product);
        item.setIngredient(ingredient);

        item.setId(contador++);
        itens.add(item);

        return item;
    }

    public List<RecipeItem> listar() {
        return itens;
    }

    private BigDecimal calcularCustoIngredientes(Long productId) {
        return itens.stream()
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
