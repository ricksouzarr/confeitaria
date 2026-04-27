package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductCategoryRepository
        extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByAtivoTrue();
}