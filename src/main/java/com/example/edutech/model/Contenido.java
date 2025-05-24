package com.example.edutech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenido")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcontenido;

    @NotBlank(message = "Este campo no puede estar vacio")
    @Column(nullable = false)
    private String nombrecontenido;

    @Lob
    @Column(name = "archivo_contenido", columnDefinition = "LONGBLOB")
    @Size(min = 1, message = "El archivo no puede estar vacío")
    @JsonIgnore
    private byte[] archivocontenido;

    @Lob
    @Column(name = "video_contenido", columnDefinition = "LONGBLOB")
    @Size(min = 1, message = "El archivo no puede estar vacío")
    @JsonIgnore
    private byte[] videocontenido;

    @NotBlank(message = "Este campo no puede estar vacio")
    @Column(nullable = false)
    private String descripcioncontenido;

}
