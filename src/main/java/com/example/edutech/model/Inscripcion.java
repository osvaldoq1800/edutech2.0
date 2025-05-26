package com.example.edutech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscripcion_seq")
    @SequenceGenerator(name = "inscripcion_seq", sequenceName = "inscripcion_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull
    private Curso curso;

    @CreationTimestamp
    @Column(name = "fecha_inscripcion", nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @Column(length = 255)
    private String comentario;

    public enum Estado {
        PENDIENTE,
        APROBADA,
        RECHAZADA,
        CANCELADA
    }
}