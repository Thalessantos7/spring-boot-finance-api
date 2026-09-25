package com.thales.gestor_financas.controller;

import com.thales.gestor_financas.model.Transacao;
import com.thales.gestor_financas.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoService service;

    @PostMapping
    public ResponseEntity<Transacao> criar(@RequestBody Transacao transacao) {
        Transacao novaTransacao = service.salvar(transacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        List<Transacao> transacoes = service.listarTodas();

        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}