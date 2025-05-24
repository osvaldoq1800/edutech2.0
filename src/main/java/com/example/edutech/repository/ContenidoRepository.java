package com.example.edutech.repository;

import com.example.edutech.model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio que maneja las operaciones CRUD con la base de datos para la entidad Contenido.
 
@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
}

