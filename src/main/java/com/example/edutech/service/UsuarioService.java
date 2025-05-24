package com.example.edutech.service;

import com.example.edutech.model.Usuario;
import com.example.edutech.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio de Usuario.
 */
@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuario por id
    public Optional<Usuario> obtenerPorId(Integer idusuario) {
        return usuarioRepository.findById(idusuario);
    }

    // Registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar usuario existente
    public Usuario actualizarUsuario(Integer idusuario, Usuario datosActualizados) {
        return usuarioRepository.findById(idusuario).map(usuario -> {
            usuario.setRut(datosActualizados.getRut());
            usuario.setNombre(datosActualizados.getNombre());
            usuario.setApellido(datosActualizados.getApellido());
            usuario.setEdad(datosActualizados.getEdad());
            usuario.setCorreo(datosActualizados.getCorreo());
            usuario.setUsuario(datosActualizados.getUsuario());
            usuario.setContrasena(datosActualizados.getContrasena());
            usuario.setNumerocelular(datosActualizados.getNumerocelular());
            usuario.setDescripcion(datosActualizados.getDescripcion());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idusuario));
    }

    // Eliminar usuario por id
    public void eliminarUsuario(Integer idusuario) {
        usuarioRepository.deleteById(idusuario);
    }
}
