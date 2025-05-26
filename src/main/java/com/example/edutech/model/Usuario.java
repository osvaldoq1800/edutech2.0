package com.example.edutech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Usuario con validaciones básicas para registro y perfil.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    
    @NotBlank(message = "Este RUT no puede estar vacio")
    @Column(unique = true, length = 12, nullable = false)
    private String rut;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    @Column(unique = true, nullable = false)
    private String nombre;

    
    @NotBlank(message = "Este campo no puede estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(nullable = false)
    private Integer edad;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Column(unique = true, nullable = false)
    private String correo;

    @NotBlank(message = "Este campo no puede estar vacio")
    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @NotBlank(message = "Este campo no puede estar vacio")
    @Column(name = "Numero Celular", nullable = false)
    private String numerocelular;

    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
    private String descripcion;

}

