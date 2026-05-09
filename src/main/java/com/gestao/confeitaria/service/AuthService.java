// src/main/java/com/gestao/confeitaria/service/AuthService.java
package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.auth.LoginRequest;
import com.gestao.confeitaria.dto.auth.LoginResponse;
import com.gestao.confeitaria.dto.auth.RegisterRequest;
import com.gestao.confeitaria.entity.*;
import com.gestao.confeitaria.repository.UsuarioRepository;
import com.gestao.confeitaria.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.senha()));

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow();

        String accessToken = jwtService.gerarToken(usuario);
        RefreshToken refreshToken = refreshTokenService.criar(usuario);

        return new LoginResponse(
                accessToken,
                refreshToken.getToken(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public LoginResponse refresh(String refreshTokenStr) {
        RefreshToken refreshToken = refreshTokenService.validar(refreshTokenStr);
        Usuario usuario = refreshToken.getUsuario();

        String novoAccessToken = jwtService.gerarToken(usuario);
        // rotaciona o refresh token a cada uso
        RefreshToken novoRefreshToken = refreshTokenService.criar(usuario);

        return new LoginResponse(
                novoAccessToken,
                novoRefreshToken.getToken(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public LoginResponse register(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        usuarioRepository.save(usuario);

        String accessToken = jwtService.gerarToken(usuario);
        RefreshToken refreshToken = refreshTokenService.criar(usuario);

        return new LoginResponse(
                accessToken,
                refreshToken.getToken(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}