package com.upao.biblioteca.infra.repository;

import com.upao.biblioteca.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Usuario.
 * Extiende JpaRepository para proporcionar operaciones de base de datos estándar para la entidad Usuario,
 * incluyendo operaciones CRUD básicas y una operación adicional para buscar por nombre de usuario.
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

/**
 * Encuentra un usuario por su nombre de usuario.
 */
 Optional<Usuario> findByUsername(String username);
}
