package com.upao.biblioteca.infra.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta con un token JWT.
 * Utilizada para enviar el token JWT generado a los clientes tras una autenticación exitosa.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    String token; // Token JWT
}
