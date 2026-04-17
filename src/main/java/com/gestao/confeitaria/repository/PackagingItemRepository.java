package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.PackagingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackagingItemRepository extends JpaRepository<PackagingItem, Long> {

    List<PackagingItem> findByProductId(Long productId);
}
