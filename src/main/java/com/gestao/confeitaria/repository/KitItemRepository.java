package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.KitItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitItemRepository
        extends JpaRepository<KitItem, Long> {
    List<KitItem> findByKitId(Long kitId);
}