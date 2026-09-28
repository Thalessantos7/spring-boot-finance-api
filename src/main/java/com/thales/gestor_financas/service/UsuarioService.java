package com.thales.gestor_financas.service;

import com.thales.gestor_financas.entity.Usuario;
import com.thales.gestor_financas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }

        String senhaEncriptada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);

        return repository.save(usuario);
    }
}