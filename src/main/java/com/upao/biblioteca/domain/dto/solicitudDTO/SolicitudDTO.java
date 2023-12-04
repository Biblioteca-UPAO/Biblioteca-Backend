package com.upao.biblioteca.domain.dto.solicitudDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private Long solicitudId;
    private LocalDateTime fechaSolicitada;
    private LocalDate fechaRecojo; 
    private String tituloLibro;
    private String nombreUsuario;
}


