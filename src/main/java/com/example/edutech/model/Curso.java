package com.example.edutech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq")
    @SequenceGenerator(name = "curso_seq", sequenceName = "curso_seq", allocationSize = 1)
    @Column(name = "ID") // Aquí va el nombre exacto en BD
    private Integer idcurso;

    @NotBlank
    @Size(max = 50, message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 500)
    @Column(nullable = true)  // En tu tabla descripción puede ser nulo
    private String descripcion;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String instructor;

    @NotNull
    @Column(nullable = false)
    private Integer duracion;
}