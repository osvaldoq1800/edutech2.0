package com.example.edutech.controller;

import com.example.edutech.model.Usuario;
import com.example.edutech.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador REST para operaciones de Usuario: registro, ver perfil, editar y eliminar.
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar usuario - POST /api/usuarios/registro
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Ver perfil usuario por id - GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> verPerfil(@PathVariable Integer id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Editar perfil usuario - PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarPerfil(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar perfil usuario - DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //Listar todos los usuarios - GET /api/usuarios
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarUsuarios();
    }
}
