
package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.dto.auth.LoginRequest;
import com.gestao.confeitaria.dto.auth.LoginResponse;
import com.gestao.confeitaria.dto.auth.RefreshRequest;
import com.gestao.confeitaria.dto.auth.RegisterRequest;
import com.gestao.confeitaria.entity.Usuario;
import com.gestao.confeitaria.repository.UsuarioRepository;
import com.gestao.confeitaria.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refresh(request.refreshToken()));
    }

    @PostMapping("/reset-dev")
    public ResponseEntity<String> resetDev(@RequestParam String email,
                                           @RequestParam String senha) {
        Usuario u = usuarioRepository.findByEmail(email).orElseThrow();
        u.setSenha(passwordEncoder.encode(senha));
        usuarioRepository.save(u);
        return ResponseEntity.ok("Senha resetada com sucesso");
    }
}