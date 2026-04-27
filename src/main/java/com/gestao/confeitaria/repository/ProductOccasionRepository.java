package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.ProductOccasion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductOccasionRepository
        extends JpaRepository<ProductOccasion, Long> {
    List<ProductOccasion> findByAtivoTrue();
}