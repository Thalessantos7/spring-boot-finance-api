package com.thales.gestor_financas.service;

import com.thales.gestor_financas.dto.ResumoFinanceiroDTO;
import com.thales.gestor_financas.enums.TipoTransacao;
import com.thales.gestor_financas.entity.Transacao;
import com.thales.gestor_financas.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @Mock
    private TransacaoRepository repository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void deveRetornarResumoFinanceiroComSucesso() {
        when(repository.somarValoresPorTipo(TipoTransacao.RECEITA)).thenReturn(new BigDecimal("5000.00"));

        when(repository.somarValoresPorTipo(TipoTransacao.DESPESA)).thenReturn(new BigDecimal("1500.00"));

        ResumoFinanceiroDTO resumo = transacaoService.obterResumo();

        assertEquals(new BigDecimal("5000.00"), resumo.receitas());
        assertEquals(new BigDecimal("1500.00"), resumo.despesas());
        assertEquals(new BigDecimal("3500.00"), resumo.saldo());
    }

    @Test
    void deveSalvarTransacaoComSucesso() {
        Transacao transacaoNova = new Transacao();
        transacaoNova.setDescricao("Freelance");
        transacaoNova.setValor(new BigDecimal("800.00"));
        transacaoNova.setTipo(TipoTransacao.RECEITA);

        Transacao transacaoSalva = new Transacao();
        transacaoSalva.setId(1L);
        transacaoSalva.setDescricao("Freelance");
        transacaoSalva.setValor(new BigDecimal("800.00"));
        transacaoSalva.setTipo(TipoTransacao.RECEITA);

        when(repository.save(any(Transacao.class))).thenReturn(transacaoSalva);

        Transacao resultado = transacaoService.salvar(transacaoNova);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Freelance", resultado.getDescricao());
        assertEquals(new BigDecimal("800.00"), resultado.getValor());

        verify(repository, times(1)).save(transacaoNova);
    }

    @Test
    void deveBuscarTransacaoPorIdComSucesso() {
        Transacao transacaoSalva = new Transacao();
        transacaoSalva.setId(1L);
        transacaoSalva.setDescricao("Salário");
        transacaoSalva.setValor(new BigDecimal("3000.00"));
        transacaoSalva.setTipo(TipoTransacao.RECEITA);

        when(repository.findById(1L)).thenReturn(Optional.of(transacaoSalva));

        Transacao resultado = transacaoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Salário", resultado.getDescricao());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoEncontrada() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException excecao = assertThrows(EntityNotFoundException.class, () -> {
            transacaoService.buscarPorId(99L);
        });

        assertEquals("Transação não encontrada", excecao.getMessage());
        verify(repository, times(1)).findById(99L);
    }
}