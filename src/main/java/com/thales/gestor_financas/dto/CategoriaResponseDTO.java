package com.thales.gestor_financas.dto;

import com.thales.gestor_financas.entity.Categoria;

public class CategoriaResponseDTO {
    private Long id;
    private String nome;
    private UsuarioResponseDTO usuarioResponseDTO;

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();

        if (categoria.getUsuario() != null) {
            this.usuarioResponseDTO = new UsuarioResponseDTO(categoria.getUsuario());
        }
    }
}