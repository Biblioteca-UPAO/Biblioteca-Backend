package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Libro.
 * Extiende JpaRepository para proporcionar operaciones de base de datos para la entidad Libro.
 * Incluye métodos personalizados para buscar libros por título y autor, y para obtener una lista paginada de libros.
 */

public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Encuentra todos los libros y los devuelve en un formato paginado, ordenados alfabéticamente por título.
     *
     * @param pageable Parámetros de paginación y ordenación.
     * @return Una página de libros ordenada por título.
     */

    Page<Libro> findAllByOrderByTituloAsc(Pageable pageable);

    /**
     * Busca un libro específico por su título y el nombre de su autor.
     *
     * @param titulo El título del libro.
     * @param autorNombre El nombre del autor del libro.
     * @return Un Optional que contiene el libro si es encontrado, o un Optional vacío si no se encuentra.
     */

    Optional<Libro> findByTituloAndAutorNombre(String titulo, String autorNombre);
}
