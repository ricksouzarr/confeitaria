package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}