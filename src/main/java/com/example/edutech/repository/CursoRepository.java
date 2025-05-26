package com.example.edutech.repository;

import com.example.edutech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Método personalizado para buscar cursos por nombre (opcional)
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
}