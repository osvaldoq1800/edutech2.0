package com.example.edutech.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer idusuario;
    private String nombre;
    private String apellido;
    private String correo;
}