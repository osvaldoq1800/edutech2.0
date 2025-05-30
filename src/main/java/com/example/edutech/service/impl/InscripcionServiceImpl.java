package com.example.edutech.service.impl;

import com.example.edutech.model.Curso;
import com.example.edutech.model.Inscripcion;
import com.example.edutech.model.Usuario;
import com.example.edutech.repository.InscripcionRepository;
import com.example.edutech.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    @Override
    @Transactional
    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
public Optional<Inscripcion> obtenerPorId(Long id) {
    Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);

    inscripcionOpt.ifPresent(inscripcion -> {
        // Forzamos que se carguen los datos del usuario
        Usuario usuario = inscripcion.getUsuario();
        if (usuario != null) {
            usuario.getNombre();
            usuario.getApellido();
            usuario.getRut();
            usuario.getCorreo();
            usuario.getEdad();
            usuario.getUsuario();
            usuario.getNumerocelular();
            usuario.getDescripcion();
        }

        // Forzamos que se carguen los datos del curso
        Curso curso = inscripcion.getCurso();
        if (curso != null) {
            curso.getNombre();
            curso.getDescripcion();
            curso.getInstructor();
            curso.getDuracion();
        }
    });

    return inscripcionOpt;
}

    @Override
    @Transactional
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcion) {
        return obtenerPorId(id).map(inscExistente -> {
            inscExistente.setCurso(inscripcion.getCurso());
            inscExistente.setUsuario(inscripcion.getUsuario());
            inscExistente.setEstado(inscripcion.getEstado());
            inscExistente.setFechaInscripcion(inscripcion.getFechaInscripcion());
            return inscripcionRepository.save(inscExistente);
        }).orElseThrow(() -> new RuntimeException("Inscripción no encontrada con id " + id));
    }

    @Override
    @Transactional
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada con id " + id);
        }
        inscripcionRepository.deleteById(id);
    }

    @Override
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }
}