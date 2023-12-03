package com.upao.biblioteca.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de SpringDoc para la generación de documentación de API automática.
 * Define la configuración de OpenAPI para la documentación de la API de la Biblioteca UPAO.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@Configuration
public class SpringDocConfigurations {

    /**
     * Configura y personaliza el objeto OpenAPI.
     * Incluye información sobre la API, como título, descripción, y detalles de contacto y licencia.
     * También define esquemas de seguridad para JWT.
     *
     * @return El objeto OpenAPI configurado.
     */

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Biblioteca upao API")
                        .description("API para el proyecto de Biblioteca upao")
                        .contact(new Contact()
                                .name("Jhoel Maqui")
                                .email("jmaquis1@upao.edu.pe"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }

    /**
     * Método de prueba para verificar la funcionalidad.
     */

    public void message(){
        System.out.println("bearer is working");
    }
}
