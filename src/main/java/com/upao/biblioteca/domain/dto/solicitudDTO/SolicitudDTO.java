package com.upao.biblioteca.domain.dto.solicitudDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para una solicitud.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {

    /**
     * Identificador único de la solicitud.
     */

    private Long solicitudId;

    /**
     * Fecha y hora en que se realizó la solicitud.
     */

    private LocalDateTime fechaSolicitada;

    /**
     * Fecha en que se planea recoger el libro solicitado.
     */

    private LocalDate fechaRecojo;

    /**
     * Título del libro solicitado.
     */

    private String tituloLibro;

    /**
     * Nombre del usuario que realizó la solicitud.
     */

    private String nombreUsuario;
}


