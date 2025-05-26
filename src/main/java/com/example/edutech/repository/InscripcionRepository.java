package com.example.edutech.repository;

import com.example.edutech.model.Curso;
import com.example.edutech.model.Inscripcion;
import com.example.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByUsuario(Usuario usuario);
    List<Inscripcion> findByCurso(Curso curso);
    boolean existsByUsuarioAndCurso(Usuario usuario, Curso curso);
}