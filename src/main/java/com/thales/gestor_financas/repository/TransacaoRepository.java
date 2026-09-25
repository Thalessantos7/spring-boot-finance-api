package com.thales.gestor_financas.repository;

import com.thales.gestor_financas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {}