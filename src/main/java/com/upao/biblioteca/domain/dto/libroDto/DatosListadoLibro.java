package com.upao.biblioteca.domain.dto.libroDto;

import com.upao.biblioteca.domain.entity.Estado;
import com.upao.biblioteca.domain.entity.Libro;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Libro}
 * Utilizado para representar los datos esenciales de un libro en listados o catálogos.
 * Incluye información como título, estado, portada, ID del autor y nombre del autor.
 *
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */
public record DatosListadoLibro(String titulo, Estado estado, String portada, Long autorAutorId,
                                String autorNombre) implements Serializable {
    /**
     * Construye un {@link DatosListadoLibro} basado en una entidad {@link Libro}.
     *
     * @param libro La entidad libro de la cual se extraerán los datos.
     */

    public DatosListadoLibro(Libro libro){
        this(libro.getTitulo(), libro.getEstado(), libro.getPortada(), libro.getAutor().getAutorId(), libro.getAutor().getNombre());
    }
}