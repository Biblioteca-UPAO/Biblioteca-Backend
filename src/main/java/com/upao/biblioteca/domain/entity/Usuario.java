package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Representa un usuario en el contexto de una biblioteca.
 * Implementa UserDetails para integrarse con la seguridad de Spring.
 * Incluye información personal y credenciales de acceso, además de la autoridad del rol del usuario.
 */

@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Entity(name = "Usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "usuarioId")
public class Usuario implements UserDetails {

    /**
     * Identificador único del usuario.
     */

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    /**
     * Nombre completo del usuario.
     */

    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Edad del usuario.
     */

    @Column(length = 3)
    private String edad;

    /**
     * Número de teléfono del usuario.
     */

    @Column(unique = true, length = 9)
    private String telefono;

    /**
     * Correo electrónico del usuario, debe ser válido.
     */

    @Column(unique = true, nullable = false, length = 100)
    @Email
    private String correo;

    /**
     * Nombre de usuario para inicio de sesión.
     */

    @Column(nullable = false, length = 100)
    private String username;

    /**
     * Contraseña para inicio de sesión.
     */

    @Column(nullable = false, length = 100)
    private String password;

    /**
     * Documento Nacional de Identidad (DNI) del usuario.
     */

    @Column(unique = true, nullable = false, length = 8)
    @Pattern(regexp = "\\d{8}")
    private String dni;

    /**
     * Rol del usuario en el sistema.
     */

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
