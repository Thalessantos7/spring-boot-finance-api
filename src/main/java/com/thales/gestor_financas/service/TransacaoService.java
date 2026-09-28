package com.thales.gestor_financas.service;

import com.thales.gestor_financas.dto.ResumoFinanceiroDTO;
import com.thales.gestor_financas.entity.TipoTransacao;
import com.thales.gestor_financas.entity.Transacao;
import com.thales.gestor_financas.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final TransacaoRepository repository;

    public Transacao salvar(Transacao transacao) {
        return repository.save(transacao);
    }

    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public ResumoFinanceiroDTO obterResumo() {
        BigDecimal totalReceitas = repository.somarValoresPorTipo(TipoTransacao.RECEITA);
        BigDecimal totalDespesas = repository.somarValoresPorTipo(TipoTransacao.DESPESA);

        BigDecimal saldo = totalReceitas.subtract(totalDespesas);

        return new ResumoFinanceiroDTO(totalReceitas, totalDespesas, saldo);
    }
}