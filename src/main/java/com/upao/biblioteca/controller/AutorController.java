package com.upao.biblioteca.controller;

import com.upao.biblioteca.domain.dto.autorDto.DatosRegistroAutor;
import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Controlador REST para operaciones relacionadas con autores.
 * Proporciona endpoints para crear y buscar autores en la base de datos.
 * Utiliza {@link AutorService} para realizar operaciones de negocio.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@RestController
@RequestMapping("/autor")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AutorController {
    @Autowired
    private final AutorService autorService;

    /**
     * Crea un nuevo autor y lo guarda en la base de datos.
     *
     * @param datosRegistroAutor DTO con los datos del autor a crear.
     * @return Una ResponseEntity que contiene al autor creado.
     * @throws ResponseStatusException Si el formato de la fecha es inválido.
     */

    @PostMapping("/crear-autor")
    public ResponseEntity<Autor> agregarAutor(@RequestBody DatosRegistroAutor datosRegistroAutor) {
        LocalDate fechaNac;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaNac = LocalDate.parse(datosRegistroAutor.fechaNacimiento(), formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha inválido");
        }

        Autor autor = new Autor();
        autor.setNombre(datosRegistroAutor.nombre());
        autor.setNacionalidad(datosRegistroAutor.nacionalidad());
        autor.setBiografia(datosRegistroAutor.biografia());
        autor.setFechaNacimiento(fechaNac);

        Autor autorGuardado = autorService.guardarAutor(autor);
        return ResponseEntity.ok(autorGuardado);
    }

    /**
     * Busca un autor por su nombre.
     *
     * @param nombre El nombre del autor a buscar.
     * @return Una ResponseEntity que contiene al autor encontrado.
     * @throws ResponseStatusException Si no se encuentra al autor.
     */

    @GetMapping("/{nombre}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable String nombre) {
        Autor autor = autorService.buscarPorNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado"));
        return ResponseEntity.ok(autor);
    }
}
