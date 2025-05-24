package com.example.edutech.service;

import com.example.edutech.model.Contenido;
import com.example.edutech.repository.ContenidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


//Almacenar, recuperar y eliminar objetos Contenido.
 
@Service
@Transactional 
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoService(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    //Devuelve todos los registros de contenido.
     
    public List<Contenido> listarTodos() {
        return contenidoRepository.findAll();
    }

    //Obtiene un contenido por su ID.
     
    public Optional<Contenido> obtenerPorId(Integer id) {
        return contenidoRepository.findById(id);
    }

    //Guarda un nuevo contenido con texto, archivo y video.
    
    public Contenido guardarContenido(String nombre, String descripcion,
                                       MultipartFile archivo, MultipartFile video) throws IOException {
        Contenido contenido = new Contenido();
        contenido.setNombrecontenido(nombre);
        contenido.setDescripcioncontenido(descripcion);
        contenido.setArchivocontenido(archivo != null ? archivo.getBytes() : null);
        contenido.setVideocontenido(video != null ? video.getBytes() : null);
        return contenidoRepository.save(contenido);
    }

    //Elimina un contenido por su ID.

    public void eliminarContenido(Integer id) {
        contenidoRepository.deleteById(id);
    }
}
