package com.gestao.confeitaria.config;

import com.gestao.confeitaria.entity.Usuario;
import com.gestao.confeitaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("admin@confyx.com.br").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@confyx.com.br");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setAtivo(true);
            usuarioRepository.save(admin);
            log.info("Usuário admin criado com sucesso.");
        }
    }
}