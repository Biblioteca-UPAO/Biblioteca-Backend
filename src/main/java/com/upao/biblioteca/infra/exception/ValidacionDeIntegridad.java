package com.upao.biblioteca.infra.exception;

/**
 * Excepción personalizada que representa un error de integridad en la aplicación.
 * Esta excepción se lanza cuando se incumplen ciertas reglas de negocio o restricciones de integridad.
 */

public class ValidacionDeIntegridad extends RuntimeException {

    /**
     * Constructor para crear una nueva instancia de ValidacionDeIntegridad.
     *
     * @param s Mensaje de error que describe la violación de integridad.
     */

      public ValidacionDeIntegridad(String s) {
        super(s);
      }
}
