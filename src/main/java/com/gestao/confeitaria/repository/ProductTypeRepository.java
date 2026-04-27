package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductTypeRepository
        extends JpaRepository<ProductType, Long> {
    List<ProductType> findByAtivoTrue();
}