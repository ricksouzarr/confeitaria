package com.gestao.confeitaria.repository;

import com.gestao.confeitaria.entity.Ingredient;
import com.gestao.confeitaria.entity.Labor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborRepository extends JpaRepository<Labor, Long> {
}
