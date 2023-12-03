package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Editorial.
 * Extiende JpaRepository para proporcionar operaciones de base de datos para la entidad Editorial,
 * incluyendo operaciones CRUD básicas.
 */

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    // Métodos CRUD básicos proporcionados por JpaRepository
}
