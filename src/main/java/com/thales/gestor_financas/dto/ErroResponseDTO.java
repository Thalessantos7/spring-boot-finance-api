package com.thales.gestor_financas.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem
) {}