package com.thales.gestor_financas.service;

import com.thales.gestor_financas.entity.Categoria;
import com.thales.gestor_financas.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void deveSalvarCategoriaComSucesso() {
        Categoria categoriaNova = new Categoria();
        categoriaNova.setNome("Alimentação");

        Categoria categoriaSalva = new Categoria();
        categoriaSalva.setId(1L);
        categoriaSalva.setNome("Alimentação");

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaSalva);

        Categoria resultado = categoriaService.salvar(categoriaNova);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Alimentação", resultado.getNome());
        verify(categoriaRepository, times(1)).save(categoriaNova);
    }

    @Test
    void deveListarCategoriasPorUsuarioComSucesso() {
        Categoria categoria1 = new Categoria();
        categoria1.setId(1L);
        categoria1.setNome("Transporte");

        Categoria categoria2 = new Categoria();
        categoria2.setId(2L);
        categoria2.setNome("Lazer");

        when(categoriaRepository.findByUsuarioId(1L)).thenReturn(List.of(categoria1, categoria2));

        List<Categoria> resultado = categoriaService.listarPorUsuario(1L);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Transporte", resultado.get(0).getNome());
        verify(categoriaRepository, times(1)).findByUsuarioId(1L);
    }

    @Test
    void deveRetornarListaVaziaQuandoUsuarioNaoTiverCategorias() {
        when(categoriaRepository.findByUsuarioId(99L)).thenReturn(Collections.emptyList());

        List<Categoria> resultado = categoriaService.listarPorUsuario(99L);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(categoriaRepository, times(1)).findByUsuarioId(99L);
    }
}