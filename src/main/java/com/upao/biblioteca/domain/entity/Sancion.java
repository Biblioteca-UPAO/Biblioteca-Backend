package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Representa una sanción en el contexto de una biblioteca.
 * Cada sanción tiene un identificador único, una fecha de inicio y una fecha de cierre.
 * La fecha de inicio se establece automáticamente al momento de la creación de la sanción.
 */

@Table(name = "sanciones")
@Entity(name = "Sancion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sancionId")
public class Sancion {

    /**
     * Identificador único de la sanción.
     */

    @Id
    @Column(name = "sancion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sancionId;

    /**
     * Fecha y hora de inicio de la sanción.
     * Se establece automáticamente al momento de la creación.
     */

    @Column(name = "fecha_inicio", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de cierre de la sanción.
     * Se debe establecer manualmente al resolver la sanción.
     */

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
}
