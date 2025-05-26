package com.example.edutech.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class Autenticacion {

    @Data
    public static class LoginRequest {
        @NotBlank(message = "El usuario es obligatorio")
        private String usuariologin;

        @NotBlank(message = "La contraseña es obligatoria")
        private String contrasenalogin;
    }

    @Data
    public static class RecuperarContrasenaRequest {
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Debe ser un correo válido")
        private String correorecuperar;
    }
}
