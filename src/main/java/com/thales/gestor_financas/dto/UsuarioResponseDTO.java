package com.thales.gestor_financas.dto;

import com.thales.gestor_financas.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}