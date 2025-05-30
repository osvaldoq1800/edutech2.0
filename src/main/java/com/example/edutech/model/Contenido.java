package com.example.edutech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "contenido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contenido_seq")
    @SequenceGenerator(name = "contenido_seq", sequenceName = "contenido_seq", allocationSize = 1)
    private Integer idcontenido;

    @NotBlank(message = "Este campo no puede estar vacío")
    @Column(nullable = false)
    private String nombrecontenido;

    @Lob
    @Column(name = "archivo_contenido")
    @JsonIgnore
    private byte[] archivocontenido;

    @Lob
    @Column(name = "video_contenido")
    @JsonIgnore
    private byte[] videocontenido;

    @NotBlank(message = "Este campo no puede estar vacío")
    @Column(nullable = false)
    private String descripcioncontenido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}