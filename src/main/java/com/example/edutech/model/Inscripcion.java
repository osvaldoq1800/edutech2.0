package com.example.edutech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    // Relación con el curso
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull(message = "El curso es obligatorio")
    private Curso curso;

    // Fecha de inscripción automática
    @CreationTimestamp
    @Column(name = "fecha_inscripcion", nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    // Estado de inscripción
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoInscripcion estado = EstadoInscripcion.PENDIENTE;

    // Comentario opcional
    @Column(length = 255)
    private String comentario;

    // Enum interno o externo
    public enum EstadoInscripcion {
        PENDIENTE,
        APROBADA,
        RECHAZADA,
        CANCELADA
    }
}
