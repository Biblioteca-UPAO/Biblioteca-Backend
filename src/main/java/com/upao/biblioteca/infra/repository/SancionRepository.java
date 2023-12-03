package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Sanción.
 * Extiende JpaRepository para proporcionar operaciones de base de datos estándar para la entidad Sanción,
 * incluyendo operaciones CRUD básicas.
 */

public interface SancionRepository extends JpaRepository<Sancion, Long> {
    // Métodos CRUD básicos proporcionados por JpaRepository
}
