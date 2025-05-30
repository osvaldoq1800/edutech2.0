package com.example.edutech.service;

import com.example.edutech.model.Contenido;
import com.example.edutech.model.Curso;
import com.example.edutech.repository.ContenidoRepository;
import com.example.edutech.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;
    private final CursoRepository cursoRepository;

    // Listar todos los contenidos
    public List<Contenido> listarTodos() {
        return contenidoRepository.findAll();
    }

    // Obtener contenido por ID
    public Optional<Contenido> obtenerPorId(Integer id) {
        return contenidoRepository.findById(id);
    }

    // Guardar contenido usando multipart/form-data (nombre, descripcion, archivos, idcurso)
    public Contenido guardarContenido(String nombre, String descripcion,
                                      MultipartFile archivo, MultipartFile video,
                                      Integer idcurso) throws IOException {

        Curso curso = cursoRepository.findById(idcurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Contenido contenido = new Contenido();
        contenido.setNombrecontenido(nombre);
        contenido.setDescripcioncontenido(descripcion);
        contenido.setArchivocontenido(archivo != null && !archivo.isEmpty() ? archivo.getBytes() : null);
        contenido.setVideocontenido(video != null && !video.isEmpty() ? video.getBytes() : null);
        contenido.setCurso(curso);

        return contenidoRepository.save(contenido);
    }

    // Guardar contenido desde JSON (el objeto Contenido ya incluye el Curso con idcurso)
    public Contenido guardar(Contenido contenido) {
        if (contenido.getCurso() == null || contenido.getCurso().getIdcurso() == null) {
            throw new RuntimeException("Debe incluir un curso con idcurso válido");
        }

        Curso curso = cursoRepository.findById(contenido.getCurso().getIdcurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        contenido.setCurso(curso);

        return contenidoRepository.save(contenido);
    }

    // Eliminar contenido por ID
    public void eliminarContenido(Integer id) {
        contenidoRepository.deleteById(id);
    }
}