package com.example.edutech.controller;

import com.example.edutech.model.Contenido;
import com.example.edutech.service.ContenidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/contenido")
@RequiredArgsConstructor
public class ContenidoController {

    private final ContenidoService contenidoService;

    @GetMapping
    public List<Contenido> listar() {
        return contenidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtener(@PathVariable Integer id) {
        return contenidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> guardarContenido(
            @RequestParam("nombrecontenido") String nombre,
            @RequestParam("descripcioncontenido") String descripcion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            @RequestParam(value = "video", required = false) MultipartFile video) {
        try {
            Contenido guardado = contenidoService.guardarContenido(nombre, descripcion, archivo, video);
            return ResponseEntity.ok(guardado);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al procesar archivos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (contenidoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(404).body("El contenido con ID " + id + " no existe.");
        }
        contenidoService.eliminarContenido(id);
        return ResponseEntity.ok().build();
    }
}