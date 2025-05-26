package com.example.edutech.service;

import com.example.edutech.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> obtenerPorId(Integer id);
    Usuario actualizarUsuario(Integer id, Usuario usuario);
    void eliminarUsuario(Integer id);
    List<Usuario> listarUsuarios();
}