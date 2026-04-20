package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long> {

    List<RecipeItem> findByProductId(Long productId);
}
