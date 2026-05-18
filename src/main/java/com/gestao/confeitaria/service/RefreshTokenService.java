package com.gestao.confeitaria.service;

import com.gestao.confeitaria.entity.RefreshToken;
import com.gestao.confeitaria.entity.Usuario;
import com.gestao.confeitaria.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration; // em ms

    private final RefreshTokenRepository repository;

    @Transactional
    public RefreshToken criar(Usuario usuario) {
        // revoga token anterior se existir
        repository.deleteByUsuario(usuario);
        repository.flush();

        RefreshToken refreshToken = RefreshToken.builder()
                .usuario(usuario)
                .token(UUID.randomUUID().toString())
                .expiracao(Instant.now().plusMillis(refreshExpiration))
                .build();

        return repository.save(refreshToken);
    }

    public RefreshToken validar(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Refresh token inválido"));

        if (refreshToken.isExpirado()) {
            repository.delete(refreshToken);
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Refresh token expirado. Faça login novamente.");
        }

        return refreshToken;
    }

    @Transactional
    public void revogar(Usuario usuario) {
        repository.deleteByUsuario(usuario);
    }
}