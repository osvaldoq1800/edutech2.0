package com.example.edutech.service;

import com.example.edutech.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    Curso crearCurso(Curso curso);
    Optional<Curso> obtenerPorId(Integer id);
    Curso actualizarCurso(Integer id, Curso curso);
    void eliminarCurso(Integer id);
    List<Curso> listarCurso();
}