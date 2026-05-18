package com.gestao.confeitaria.controller;

import com.gestao.confeitaria.dto.usuario.*;
import com.gestao.confeitaria.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(
            @Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(
            @PathVariable Long id,
            @Valid @RequestBody TrocaSenhaDTO dto) {
        usuarioService.trocarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/alternar-ativo")
    public ResponseEntity<UsuarioResponseDTO> alternarAtivo(
            @PathVariable Long id) {
        usuarioService.alternarAtivo(id);
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PatchMapping("/{id}/resetar-senha")
    public ResponseEntity<Void> resetarSenha(
            @PathVariable Long id,
            @RequestParam String novaSenha) {
        usuarioService.resetarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }
}