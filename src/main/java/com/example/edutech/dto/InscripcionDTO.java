package com.example.edutech.dto;

import lombok.Data;

@Data
public class InscripcionDTO {
    private Long usuarioId;
    private Integer cursoId;
    private String estado;
}