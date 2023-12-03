package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.service.AutorService;
import com.upao.biblioteca.infra.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para AutorService.
 * Utiliza Mockito para simular las interacciones con AutorRepository.
 * @author Jhoel Maqui & James Huaman
 * @version 1.0
 */

@SpringBootTest
public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    /**
     * Prueba la búsqueda de autores por nombre cuando el autor existe.
     * Verifica que el resultado sea presente y corresponda al autor buscado.
     */

    @Test
    //buscarPorNombre
    public void cuandoBuscaPorNombreExistente_retornaAutor() {

        Autor autor = new Autor();
        autor.setAutorId(1L);
        autor.setNombre("Nombre Autor");
        autor.setNacionalidad("Nacionalidad");
        autor.setBiografia("Biografía");
        autor.setFechaNacimiento(LocalDate.now());

        when(autorRepository.findByNombre("Nombre Autor")).thenReturn(Optional.of(autor));

        Optional<Autor> resultado = autorService.buscarPorNombre("Nombre Autor");

        assertTrue(resultado.isPresent());
        assertEquals("Nombre Autor", resultado.get().getNombre());

    }

    /**
     * Prueba la búsqueda de autores por nombre cuando el autor no existe.
     * Verifica que el resultado sea vacío.
     */

    @Test
    public void cuandoBuscaPorNombreInexistente_retornaVacio() {

        when(autorRepository.findByNombre("Nombre Inexistente")).thenReturn(Optional.empty());

        Optional<Autor> resultado = autorService.buscarPorNombre("Nombre Inexistente");

        assertFalse(resultado.isPresent());

    }

    /**
     * Prueba el guardado de un autor.
     * Verifica que el autor guardado sea retornado y sus datos sean correctos.
     */

    @Test
    //guardarAutor
    public void cuandoGuardaAutor_retornaAutorGuardado() {

        Autor autor = new Autor();
        autor.setAutorId(1L);
        autor.setNombre("Nombre Autor");
        autor.setNacionalidad("Nacionalidad");
        autor.setBiografia("Biografía");
        autor.setFechaNacimiento(LocalDate.now());

        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor resultado = autorService.guardarAutor(autor);

        assertNotNull(resultado);
        assertEquals("Nombre Autor", resultado.getNombre());
    }


}