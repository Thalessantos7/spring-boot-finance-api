package com.thales.gestor_financas.service;

import com.thales.gestor_financas.entity.Usuario;
import com.thales.gestor_financas.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveRegistrarUsuarioComSucessoEEncriptarSenha() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail("teste@email.com");
        novoUsuario.setSenha("senhaLimpa123");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setEmail("teste@email.com");
        usuarioSalvo.setSenha("hashSeguro$2a$10$...");

        when(repository.findByEmail("teste@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senhaLimpa123")).thenReturn("hashSeguro$2a$10$...");
        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.registrar(novoUsuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("hashSeguro$2a$10$...", resultado.getSenha());

        verify(repository, times(1)).findByEmail("teste@email.com");
        verify(passwordEncoder, times(1)).encode("senhaLimpa123");
        verify(repository, times(1)).save(novoUsuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaEstiverEmUso() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setEmail("teste@email.com");

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail("teste@email.com");
        novoUsuario.setSenha("senhaLimpa123");

        when(repository.findByEmail("teste@email.com")).thenReturn(Optional.of(usuarioExistente));

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.registrar(novoUsuario);
        });

        assertEquals("Este e-mail já está em uso.", excecao.getMessage());

        verify(repository, times(1)).findByEmail("teste@email.com");
        verify(passwordEncoder, never()).encode(anyString());
        verify(repository, never()).save(any(Usuario.class));
    }
}