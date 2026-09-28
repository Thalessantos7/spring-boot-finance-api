package com.thales.gestor_financas.controller;

import com.thales.gestor_financas.entity.Categoria;
import com.thales.gestor_financas.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    public final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Categoria>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(categoriaService.listarPorUsuario(usuarioId));
    }
}