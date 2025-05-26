package com.example.edutech.controller;

import com.example.edutech.model.Autenticacion.LoginRequest;
import com.example.edutech.model.Autenticacion.RecuperarContrasenaRequest;
import com.example.edutech.model.Usuario;
import com.example.edutech.service.AutenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = autenticacionService.iniciarSesion(loginRequest);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body(new Usuario())); // Retorna un objeto vacío
    }

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperar(@Valid @RequestBody RecuperarContrasenaRequest request) {
        Optional<Usuario> usuario = autenticacionService.recuperarPorCorreo(request);
        return usuario.map(u -> ResponseEntity.ok("Se han enviado instrucciones a: " + request.getCorreorecuperar()))
                .orElse(ResponseEntity.status(404).body("Correo no encontrado"));
    }
}