package com.gestao.confeitaria.dto.auth;

public record LoginResponse(
        String token,
        String refreshToken,
        String nome,
        String email
) {}