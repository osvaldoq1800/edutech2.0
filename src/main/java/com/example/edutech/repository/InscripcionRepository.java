package com.example.edutech.repository;

import com.example.edutech.model.Curso;
import com.example.edutech.model.Inscripcion;
import com.example.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByUsuario(Usuario usuario);
    List<Inscripcion> findByCurso(Curso curso);
    boolean existsByUsuarioAndCurso(Usuario usuario, Curso curso);
}
