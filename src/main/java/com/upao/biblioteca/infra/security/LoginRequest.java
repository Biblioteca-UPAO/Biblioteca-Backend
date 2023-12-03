package com.upao.biblioteca.infra.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo para representar una solicitud de inicio de sesión.
 * Contiene los campos necesarios para autenticar a un usuario, como el nombre de usuario y la contraseña.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username; // Nombre de usuario
    String password; // Contraseña
}
