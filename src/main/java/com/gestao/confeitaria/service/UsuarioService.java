package com.gestao.confeitaria.service;

import com.gestao.confeitaria.dto.usuario.*;
import com.gestao.confeitaria.entity.Usuario;
import com.gestao.confeitaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::from)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        return UsuarioResponseDTO.from(buscarEntidade(id));
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(dto.getRole());
        usuario.setAtivo(true);
        return UsuarioResponseDTO.from(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = buscarEntidade(id);

        if (!usuario.getEmail().equals(dto.getEmail()) &&
                usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getRole() != null) {
            usuario.setRole(dto.getRole());
        }
        if (dto.getAtivo() != null) {
            usuario.setAtivo(dto.getAtivo());
        }

        return UsuarioResponseDTO.from(usuarioRepository.save(usuario));
    }

    public void trocarSenha(Long id, TrocaSenhaDTO dto) {
        Usuario usuario = buscarEntidade(id);

        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public void alternarAtivo(Long id) {
        Usuario usuario = buscarEntidade(id);
        usuario.setAtivo(!usuario.isAtivo());
        usuarioRepository.save(usuario);
    }

    private Usuario buscarEntidade(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário não encontrado: " + id));
    }

    public void resetarSenha(Long id, String novaSenha) {
        Usuario usuario = buscarEntidade(id);
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }
}