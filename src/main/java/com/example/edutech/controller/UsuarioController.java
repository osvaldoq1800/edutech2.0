package com.example.edutech.controller;

import com.example.edutech.model.Usuario;
import com.example.edutech.service.UsuarioService;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
     public ResponseEntity<?> verPerfil(@PathVariable Integer id) {
    Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
    if (usuarioOpt.isPresent()) {
        return ResponseEntity.ok(usuarioOpt.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
}

    

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPerfil(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.obtenerPorId(id)
                .map(u -> {
                    try {
                        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
                        return ResponseEntity.ok(actualizado);
                    } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Error al actualizar usuario: " + e.getMessage());
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPerfil(@PathVariable Integer id) {
        if (usuarioService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        }
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}