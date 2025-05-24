package com.example.edutech.repository;

import com.example.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String usuario); // Buscar por nombre de usuario
    Optional<Usuario> findByCorreo(String correo);   // Buscar por correo
}

