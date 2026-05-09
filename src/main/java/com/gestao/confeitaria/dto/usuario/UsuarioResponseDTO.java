package com.gestao.confeitaria.dto.usuario;

import com.gestao.confeitaria.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Usuario.Role role;
    private boolean ativo;

    public static UsuarioResponseDTO from(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole());
        dto.setAtivo(u.isAtivo());
        return dto;
    }
}