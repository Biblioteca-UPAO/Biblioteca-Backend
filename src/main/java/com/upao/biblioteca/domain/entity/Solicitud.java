package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "solcitudes")
@Entity(name = "Solicitud")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "solicitudId")
public class Solicitud {
    @Id
    @Column(name = "solicitud_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solicitudId;
    @Column(name = "fecha_solicitada", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime fechaSolicitada;

}
