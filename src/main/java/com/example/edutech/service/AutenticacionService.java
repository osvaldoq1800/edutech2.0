package com.example.edutech.service;

import com.example.edutech.model.Usuario;
import com.example.edutech.model.Autenticacion.LoginRequest;
import com.example.edutech.model.Autenticacion.RecuperarContrasenaRequest;
import com.example.edutech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> iniciarSesion(LoginRequest loginRequest) {
        return usuarioRepository.findByUsuario(loginRequest.getUsuariologin())
                .filter(usuario -> usuario.getContrasena().equals(loginRequest.getContrasenalogin()));
    }

    public Optional<Usuario> recuperarPorCorreo(RecuperarContrasenaRequest request) {
        return usuarioRepository.findByCorreo(request.getCorreorecuperar());
    }
}