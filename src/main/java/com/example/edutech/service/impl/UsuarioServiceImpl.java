package com.example.edutech.service.impl;

import com.example.edutech.model.Usuario;
import com.example.edutech.repository.UsuarioRepository;
import com.example.edutech.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuario.getNumerocelular() == null || usuario.getNumerocelular().trim().isEmpty()) {
            usuario.setNumerocelular("+56900000000"); // 🔥 Asignamos un número por defecto si está vacío
        }
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado.");
        }
        usuario.setIdusuario(id);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}