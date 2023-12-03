package com.upao.biblioteca.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que maneja excepciones globales para la aplicación.
 * Utiliza @RestControllerAdvice para aplicar a todos los controladores REST.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@RestControllerAdvice
public class TratadorDeErrores {

    /**
     * Maneja excepciones de tipo EntityNotFoundException.
     *
     * @return Una respuesta con estado HTTP 404 Not Found.
     */

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    /**
     * Maneja excepciones de validación de argumentos de métodos.
     *
     * @param e Excepción con detalles de los errores de validación.
     * @return Una respuesta con estado HTTP 400 Bad Request y los detalles de los errores.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * Maneja excepciones personalizadas para validaciones de integridad.
     *
     * @param e Excepción con el mensaje de error.
     * @return Una respuesta con estado HTTP 400 Bad Request y el mensaje de error.
     */

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Maneja excepciones generales de validación.
     *
     * @param e Excepción con el mensaje de error.
     * @return Una respuesta con estado HTTP 400 Bad Request y el mensaje de error.
     */

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Registro que representa un error de validación específico.
     */

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
