package com.gestao.confeitaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UnitType tipo;

    @PrePersist
    @PreUpdate
    public void normalize() {
        if (sigla != null) {
            sigla = sigla.toUpperCase();
        }
    }

}