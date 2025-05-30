package com.example.edutech.service.impl;

import com.example.edutech.model.Curso;
import com.example.edutech.repository.CursoRepository;
import com.example.edutech.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCurso() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> obtenerPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Curso actualizarCurso(Integer id, Curso curso) {
        return obtenerPorId(id).map(existente -> {
            existente.setNombre(curso.getNombre());
            existente.setDescripcion(curso.getDescripcion());
            existente.setInstructor(curso.getInstructor());
            existente.setDuracion(curso.getDuracion());
            return cursoRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado con id " + id));
    }

    @Override
    @Transactional
    public void eliminarCurso(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con id " + id);
        }
        cursoRepository.deleteById(id);
    }
}