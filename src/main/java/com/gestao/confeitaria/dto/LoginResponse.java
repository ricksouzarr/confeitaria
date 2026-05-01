package com.gestao.confeitaria.dto;

public record LoginResponse(
        String token,
        String refreshToken,
        String nome,
        String email
) {}