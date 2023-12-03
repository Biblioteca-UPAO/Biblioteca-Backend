package com.upao.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para la Biblioteca UPAO.
 * Usa la anotación @SpringBootApplication para marcar la configuración automática,
 * el escaneo de componentes y la configuración adicional necesaria para una aplicación Spring Boot.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@SpringBootApplication
public class BibliotecaApplication {

	/**
	 * Punto de entrada principal para la aplicación Spring Boot.
	 *
	 * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
	 */

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
