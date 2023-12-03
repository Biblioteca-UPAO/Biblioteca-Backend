package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.infra.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio que ofrece operaciones relacionadas con libros en la biblioteca.
 * Proporciona métodos para obtener y agregar libros, interactuando con {@link LibroRepository}.
 */

@Service
public class LibroService {

    @Autowired
    private final LibroRepository libroRepository;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param libroRepository Repositorio para operaciones CRUD con libros.
     */

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /**
     * Obtiene una página de libros, ordenados alfabéticamente por título.
     *
     * @param pageable Parámetros de paginación para la consulta.
     * @return Una página de libros.
     * @throws ResponseStatusException Si no se encuentran libros.
     */

    public Page<Libro> obtenerLibros(Pageable pageable) {
        Page<Libro> libros = libroRepository.findAllByOrderByTituloAsc(pageable);

        if (libros.isEmpty() && libros.isFirst()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libros no encontrados");
        }

        return libros;
    }

    /**
     * Agrega un nuevo libro a la biblioteca.
     *
     * @param libro El libro a agregar.
     * @return El libro agregado.
     * @throws ResponseStatusException Si el libro no tiene título o autor, o si ya existe un libro con el mismo título y autor.
     */

    public Libro agregarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getAutor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y autor son obligatorios");
        }

        libroRepository.findByTituloAndAutorNombre(libro.getTitulo(), libro.getAutor().getNombre())
                .ifPresent(existingLibro -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un libro con el mismo título y autor");
                });
        return libroRepository.save(libro);
    }
}
