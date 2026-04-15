package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.FichaTecnicaResult;
import com.gestao.confeitaria.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Double calcularCustoPorProduto(Long productId) {

        Double custoIngredientes = itens.stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .mapToDouble(RecipeItem::getCustoTotal)
                .sum();

        Double custoEmbalagem = packagingItemService.calcularCustoPorProduto(productId);

        Product product = productService.buscarPorId(productId);

        Labor labor = laborService.obter();

        Double custoMaoDeObra = product.getHorasMaoDeObra() * labor.getCustoHora();

        Double custoTotal = custoIngredientes + custoEmbalagem + custoMaoDeObra;

        return custoTotal;
    }

    public Double calcularCustoPorRendimento(Long productId) {

        Product product = productService.buscarPorId(productId);

        Double custoTotal = calcularCustoPorProduto(productId);

        return custoTotal / product.getRendimento();
    }

    public Double calcularPrecoVendaTotal(Long productId) {

        Product product = productService.buscarPorId(productId);

        Double custoTotal = calcularCustoPorProduto(productId);

        return custoTotal * product.getMarkup();
    }

    public Double calcularPrecoVendaPorPorcao(Long productId) {

        Product product = productService.buscarPorId(productId);

        Double custoPorPorcao = calcularCustoPorRendimento(productId);

        return custoPorPorcao * product.getMarkup();
    }

    public FichaTecnicaResult calcularFichaTecnica(Long productId) {

        Double custoIngredientes = itens.stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .mapToDouble(RecipeItem::getCustoTotal)
                .sum();

        Double custoEmbalagem = packagingItemService.calcularCustoPorProduto(productId);

        Product product = productService.buscarPorId(productId);
        Labor labor = laborService.obter();

        Double custoMaoDeObra = product.getHorasMaoDeObra() * labor.getCustoHora();

        Double custoTotal = custoIngredientes + custoEmbalagem + custoMaoDeObra;

        Double custoPorPorcao = custoTotal / product.getRendimento();

        Double precoTotal = custoTotal * product.getMarkup();

        Double precoPorPorcao = custoPorPorcao * product.getMarkup();

        return FichaTecnicaResult.builder()
                .custoIngredientes(custoIngredientes)
                .custoEmbalagem(custoEmbalagem)
                .custoMaoDeObra(custoMaoDeObra)
                .custoTotal(custoTotal)
                .custoPorPorcao(custoPorPorcao)
                .precoTotal(precoTotal)
                .precoPorPorcao(precoPorPorcao)
                .build();
    }
}
