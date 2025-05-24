package com.example.edutech.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//Modelos para autenticación y recuperación de contraseña.

public class Autenticacion {

    //Modelo para login: usuario y contraseña.
    @Data
    public static class LoginRequest {
        @NotBlank(message = "El usuario es obligatorio")
        private String usuariologin;

        @NotBlank(message = "La contraseña es obligatoria")
        private String contrasenalogin;
    }

    //Modelo para solicitud de recuperación de contraseña vía correo. 
    @Data
    public static class RecuperarContrasenaRequest {
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Debe ser un correo válido")
        private String correorecuperar;
    }
}

