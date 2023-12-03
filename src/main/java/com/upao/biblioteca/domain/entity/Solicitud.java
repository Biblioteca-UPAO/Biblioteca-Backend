package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Representa una solicitud en el contexto de una biblioteca.
 * Cada solicitud tiene un identificador único y una fecha en la que fue solicitada.
 * La fecha de solicitud se establece automáticamente al momento de la creación de la solicitud.
 */

@Table(name = "solcitudes")
@Entity(name = "Solicitud")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "solicitudId")
public class Solicitud {

    /**
     * Identificador único de la solicitud.
     */

    @Id
    @Column(name = "solicitud_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solicitudId;

    /**
     * Fecha y hora en la que la solicitud fue realizada.
     * Se establece automáticamente al momento de la creación.
     */

    @Column(name = "fecha_solicitada", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime fechaSolicitada;
}
