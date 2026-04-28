package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.LoginRequest;
import com.gestao.confeitaria.dto.LoginResponse;
import com.gestao.confeitaria.dto.RegisterRequest;
import com.gestao.confeitaria.entity.Usuario;
import com.gestao.confeitaria.repository.UsuarioRepository;
import com.gestao.confeitaria.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.senha()));

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.gerarToken(usuario);

        return new LoginResponse(token, usuario.getNome(), usuario.getEmail());
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

        String token = jwtService.gerarToken(usuario);

        return new LoginResponse(token, usuario.getNome(), usuario.getEmail());
    }
}