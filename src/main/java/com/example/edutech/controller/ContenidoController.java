package com.example.edutech.controller;

import com.example.edutech.model.Contenido;
import com.example.edutech.service.ContenidoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//Controlador REST para exponer los endpoints de Contenido.
@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    
    //GET /api/contenido
    //Lista todos los contenidos.
    @GetMapping
    public List<Contenido> listar() {
        return contenidoService.listarTodos();
    }

    //GET /api/contenido/{id}
    //Devuelve un contenido específico por ID.
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtener(@PathVariable Integer id) {
        return contenidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST /api/contenido
    //Guarda un nuevo contenido.
    //Acepta campos tipo texto y archivos usando multipart/form-data.
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> guardarContenido(
            @RequestParam("nombrecontenido") String nombre,
            @RequestParam("descripcioncontenido") String descripcion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            @RequestParam(value = "video", required = false) MultipartFile video) {
        try {
            // Llama al servicio para guardar el contenido
            Contenido guardado = contenidoService.guardarContenido(nombre, descripcion, archivo, video);
            return ResponseEntity.ok(guardado);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al procesar archivos: " + e.getMessage());
        }
    }

    //DELETE /api/contenido/{id}
    //Elimina un contenido por ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        contenidoService.eliminarContenido(id);
        return ResponseEntity.ok().build();
    }
}
