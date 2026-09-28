package com.thales.gestor_financas.repository;

import com.thales.gestor_financas.entity.TipoTransacao;
import com.thales.gestor_financas.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.tipo = :tipo")
    BigDecimal somarValoresPorTipo(@Param("tipo") TipoTransacao tipo);
}