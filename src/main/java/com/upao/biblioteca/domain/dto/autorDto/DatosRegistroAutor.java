package com.upao.biblioteca.domain.dto.autorDto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.upao.biblioteca.domain.entity.Autor}
 * Este objeto se utiliza para transferir los datos necesarios para registrar un nuevo autor.
 * Incluye campos como nombre, nacionalidad, biografía y fecha de nacimiento.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */
public record DatosRegistroAutor(@NotNull String nombre, String nacionalidad, String biografia,
                                 String fechaNacimiento) implements Serializable {
    // Aquí no es necesario implementar métodos adicionales ya que es un record.
}