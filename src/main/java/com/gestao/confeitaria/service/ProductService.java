package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.Product;
import com.gestao.confeitaria.repository.ProductRepository;
import com.gestao.confeitaria.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductTypeService typeService;

    @Autowired
    private ProductOccasionService occasionService;


    public Product buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public Product salvar(Product product){
        return repository.save(product);
    }

    public List<Product> listar(){
        return repository.findAll();
    }

    public BigDecimal calcularCustoPorRendimento(Product product, BigDecimal custoTotal) {
        return BigDecimalUtils.scale(BigDecimalUtils.divide(custoTotal, BigDecimal.valueOf(product.getRendimento())));
    }

    public void delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        repository.delete(product);
    }

    public Product alterar(Long id, Product productAtualizado) {
        Product existente = buscarPorId(id);

        existente.setNome(productAtualizado.getNome());
        existente.setRendimento(productAtualizado.getRendimento());
        existente.setMarkupTotal(productAtualizado.getMarkupTotal());
        existente.setMarkupRendimento(productAtualizado.getMarkupRendimento());
        existente.setHorasMaoDeObra(productAtualizado.getHorasMaoDeObra());
        existente.setObservacaoFichaTecnica(
                productAtualizado.getObservacaoFichaTecnica());
        existente.setKit(productAtualizado.isKit());

        // categoria, tipo e ocasião são opcionais
        if (productAtualizado.getCategoria() != null
                && productAtualizado.getCategoria().getId() != null) {
            existente.setCategoria(
                    categoryService.buscarPorId(
                            productAtualizado.getCategoria().getId()));
        } else {
            existente.setCategoria(null);
        }

        if (productAtualizado.getTipo() != null
                && productAtualizado.getTipo().getId() != null) {
            existente.setTipo(
                    typeService.buscarPorId(
                            productAtualizado.getTipo().getId()));
        } else {
            existente.setTipo(null);
        }

        if (productAtualizado.getOcasiao() != null
                && productAtualizado.getOcasiao().getId() != null) {
            existente.setOcasiao(
                    occasionService.buscarPorId(
                            productAtualizado.getOcasiao().getId()));
        } else {
            existente.setOcasiao(null);
        }

        return repository.save(existente);
    }
}
