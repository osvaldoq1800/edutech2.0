package com.example.edutech.repository;

import com.example.edutech.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Puedes agregar consultas personalizadas aquí si lo necesitas
}