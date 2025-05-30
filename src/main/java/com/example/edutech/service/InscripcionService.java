package com.example.edutech.service;

import com.example.edutech.model.Inscripcion;

import java.util.List;
import java.util.Optional;

public interface InscripcionService {
    Inscripcion crearInscripcion(Inscripcion inscripcion);
    Optional<Inscripcion> obtenerPorId(Long id);
    Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcion);
    void eliminarInscripcion(Long id);
    List<Inscripcion> listarInscripciones();
    
}