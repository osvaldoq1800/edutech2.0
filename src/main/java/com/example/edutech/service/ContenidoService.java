package com.example.edutech.service;

import com.example.edutech.model.Contenido;
import com.example.edutech.repository.ContenidoRepository;
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

    public List<Contenido> listarTodos() {
        return contenidoRepository.findAll();
    }

    public Optional<Contenido> obtenerPorId(Integer id) {
        return contenidoRepository.findById(id);
    }

    public Contenido guardarContenido(String nombre, String descripcion,
                                      MultipartFile archivo, MultipartFile video) throws IOException {
        Contenido contenido = new Contenido();
        contenido.setNombrecontenido(nombre);
        contenido.setDescripcioncontenido(descripcion);
        contenido.setArchivocontenido(archivo != null && !archivo.isEmpty() ? archivo.getBytes() : null);
        contenido.setVideocontenido(video != null && !video.isEmpty() ? video.getBytes() : null);
        return contenidoRepository.save(contenido);
    }

    public void eliminarContenido(Integer id) {
        contenidoRepository.deleteById(id);
    }
}