package com.example.edutech.service;

import com.example.edutech.model.Usuario;
import com.example.edutech.model.Autenticacion.LoginRequest;
import com.example.edutech.model.Autenticacion.RecuperarContrasenaRequest;
import com.example.edutech.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacionService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Lógica de inicio de sesión
    public Optional<Usuario> iniciarSesion(LoginRequest loginRequest) {
        return usuarioRepository.findByUsuario(loginRequest.getUsuariologin())
                .filter(usuario -> usuario.getContrasena().equals(loginRequest.getContrasenalogin()));
    }

    // Lógica para recuperación de contraseña por correo
    public Optional<Usuario> recuperarPorCorreo(RecuperarContrasenaRequest request) {
        return usuarioRepository.findByCorreo(request.getCorreorecuperar());
    }
}
