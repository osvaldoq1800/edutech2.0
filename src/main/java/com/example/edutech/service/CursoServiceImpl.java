package com.example.edutech.service;

import com.example.edutech.model.Curso;
import com.example.edutech.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id " + id));
    }

    @Override
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizarCurso(Long id, Curso curso) {
        Curso existente = obtenerPorId(id);
        existente.setNombre(curso.getNombre());
        existente.setDescripcion(curso.getDescripcion());
        existente.setInstructor(curso.getInstructor());
        existente.setDuracion(curso.getDuracion());
        return cursoRepository.save(existente);
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}

