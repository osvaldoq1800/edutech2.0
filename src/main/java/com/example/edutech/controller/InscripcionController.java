package com.example.edutech.controller;

import com.example.edutech.dto.InscripcionDTO;
import com.example.edutech.model.Curso;
import com.example.edutech.model.Inscripcion;
import com.example.edutech.model.Usuario;
import com.example.edutech.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.listarInscripciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcionPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

@PostMapping
public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody InscripcionDTO dto) {
    Usuario usuario = new Usuario();
    usuario.setIdusuario(dto.getUsuarioId().intValue()); // convertir Long a Integer

    Curso curso = new Curso();
    curso.setIdcurso(dto.getCursoId().intValue()); // convertir Long a Integer

    Inscripcion inscripcion = new Inscripcion();
    inscripcion.setUsuario(usuario);
    inscripcion.setCurso(curso);
    inscripcion.setEstado(dto.getEstado());

    Inscripcion nueva = inscripcionService.crearInscripcion(inscripcion);
    return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
}
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable Long id,
         @Valid @RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion inscripcionActualizada = inscripcionService.actualizarInscripcion(id, inscripcion);
            return ResponseEntity.ok(inscripcionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        try {
            inscripcionService.eliminarInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}