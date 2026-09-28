package com.thales.gestor_financas.dto;

import java.math.BigDecimal;

public record ResumoFinanceiroDTO(BigDecimal receitas, BigDecimal despesas, BigDecimal saldo) {}