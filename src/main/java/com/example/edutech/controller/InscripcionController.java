package com.example.edutech.controller;

import com.example.edutech.model.Inscripcion;
import com.example.edutech.model.Usuario;
import com.example.edutech.model.Curso;
import com.example.edutech.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> listarTodas() {
        return inscripcionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarPorId(@PathVariable Long id) {
        Optional<Inscripcion> inscripcion = inscripcionService.buscarPorId(id);
        return inscripcion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Inscripcion> buscarPorUsuario(@PathVariable Usuario usuario) {
        return inscripcionService.buscarPorUsuario(usuario);
    }

    @GetMapping("/curso/{cursoId}")
    public List<Inscripcion> buscarPorCurso(@PathVariable Curso curso) {
        return inscripcionService.buscarPorCurso(curso);
    }

    @PostMapping
    public ResponseEntity<Inscripcion> guardar(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.guardar(inscripcion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}