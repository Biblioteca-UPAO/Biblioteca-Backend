package com.upao.biblioteca.domain.dto.libroDto;

import com.upao.biblioteca.domain.entity.Estado;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Libro}
 * Utilizado para encapsular los datos necesarios para el registro de un nuevo libro.
 * Incluye campos como título, estado, portada y el nombre del autor.
 */
public record DatosRegistroLibro(String titulo, Estado estado, String portada,
                                 String autorNombre) implements Serializable {
    // Como es un record, los campos son automáticamente tratados como propiedades finales.
}