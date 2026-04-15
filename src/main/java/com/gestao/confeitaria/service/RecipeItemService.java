package com.gestao.confeitaria.service;

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
}
