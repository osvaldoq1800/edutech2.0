package com.example.edutech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Integer idusuario;

    @NotBlank(message = "El rut es obligatorio")
    @Size(max = 12, message = "El rut no puede tener más de 12 caracteres")
    @Column(unique = true, length = 12, nullable = false)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false)
    private String apellido;

    @NotNull(message = "La edad es obligatoria")
    @Column(nullable = false)
    private Integer edad;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Column(unique = true, nullable = false)
    private String correo;

    @NotBlank(message = "El usuario es obligatorio")
    @Column(nullable = false)
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(nullable = false)
    private String contrasena;

    @NotBlank(message = "El número de celular es obligatorio")
    @Column(name = "numero_celular", nullable = false)
    private String numerocelular;

    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String descripcion;
}