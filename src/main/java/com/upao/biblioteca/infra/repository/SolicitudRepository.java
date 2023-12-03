package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Solicitud.
 * Extiende JpaRepository para proporcionar operaciones de base de datos estándar para la entidad Solicitud,
 * incluyendo operaciones CRUD básicas.
 */

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    // Métodos CRUD básicos proporcionados por JpaRepository
}
