package com.example.edutech.service;

import com.example.edutech.model.*;
import com.example.edutech.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listarTodas() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> buscarPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public List<Inscripcion> buscarPorUsuario(Usuario usuario) {
        return inscripcionRepository.findByUsuario(usuario);
    }

    public List<Inscripcion> buscarPorCurso(Curso curso) {
        return inscripcionRepository.findByCurso(curso);
    }

    public boolean yaInscrito(Usuario usuario, Curso curso) {
        return inscripcionRepository.existsByUsuarioAndCurso(usuario, curso);
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
