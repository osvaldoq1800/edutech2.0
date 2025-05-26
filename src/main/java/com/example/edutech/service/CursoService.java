package com.example.edutech.service;

import com.example.edutech.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listarCursos();
    Optional<Curso> obtenerPorId(Long id);
    Curso crearCurso(Curso curso);
    Curso actualizarCurso(Long id, Curso curso);
    void eliminarCurso(Long id);
}