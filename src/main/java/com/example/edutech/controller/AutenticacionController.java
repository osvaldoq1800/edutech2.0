package com.example.edutech.controller;

import com.example.edutech.model.Autenticacion.LoginRequest;
import com.example.edutech.model.Autenticacion.RecuperarContrasenaRequest;
import com.example.edutech.model.Usuario;
import com.example.edutech.service.AutenticacionService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    // POST /api/autenticacion/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = autenticacionService.iniciarSesion(loginRequest);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get()); // Aquí podrías devolver un DTO o token
        } else {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }

    // POST /api/autenticacion/recuperar
    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperar(@Valid @RequestBody RecuperarContrasenaRequest request) {
        Optional<Usuario> usuario = autenticacionService.recuperarPorCorreo(request);
        if (usuario.isPresent()) {
            return ResponseEntity.ok("Se han enviado instrucciones a: " + request.getCorreorecuperar());
        } else {
            return ResponseEntity.status(404).body("Correo no encontrado");
        }
    }
}

