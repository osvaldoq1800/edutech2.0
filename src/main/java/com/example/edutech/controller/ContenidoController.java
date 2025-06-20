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

    // Obtener todos los contenidos
    @GetMapping
    public List<Contenido> listar() {
        return contenidoService.listarTodos();
    }

    // Obtener contenido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtener(@PathVariable Integer id) {
        return contenidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear contenido vía multipart/form-data (incluye archivo, video y curso)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> guardarContenido(
            @RequestParam("nombrecontenido") String nombre,
            @RequestParam("descripcioncontenido") String descripcion,
            @RequestParam("idcurso") Integer idcurso, // <-- este es importante
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            @RequestParam(value = "video", required = false) MultipartFile video) {
        try {
            Contenido guardado = contenidoService.guardarContenido(nombre, descripcion, archivo, video, idcurso);
            return ResponseEntity.ok(guardado);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al procesar archivos: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Eliminar contenido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (contenidoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(404).body("El contenido con ID " + id + " no existe.");
        }
        contenidoService.eliminarContenido(id);
        return ResponseEntity.ok().build();
    }

    // Crear contenido vía JSON puro (si ya tienes el objeto Curso incluido)
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Contenido> crearContenidoJson(@RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenidoService.guardar(contenido));
    }
}