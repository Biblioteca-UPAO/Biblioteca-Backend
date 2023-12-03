package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Autor.
 * Extiende JpaRepository para proporcionar operaciones de base de datos para la entidad Autor.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

public interface AutorRepository extends JpaRepository<Autor, Long> {

    /**
     * Busca un autor por su nombre.
     *
     * @param nombre Nombre del autor a buscar.
     * @return Un Optional conteniendo el autor si es encontrado, o un Optional vacío si no se encuentra.
     */

    Optional<Autor> findByNombre(String nombre);
}
