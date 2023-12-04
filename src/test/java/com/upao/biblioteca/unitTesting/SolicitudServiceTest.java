package com.upao.biblioteca.unitTesting;

import com.upao.biblioteca.domain.entity.Estado;
import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.entity.Solicitud;
import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.infra.repository.LibroRepository;
import com.upao.biblioteca.infra.repository.SolicitudRepository;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import com.upao.biblioteca.domain.service.SolicitudService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private SolicitudService solicitudService;

    public SolicitudServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarSolicitud() {
        // Simular datos de entrada
        Long usuarioId = 1L;
        Long libroId = 1L;

        // Simular entidades necesarias
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);

        Libro libro = new Libro();
        libro.setLibroId(libroId);
        libro.setEstado(Estado.DISPONIBLE);

        // Configurar comportamiento de los mocks
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(libroRepository.findById(libroId)).thenReturn(Optional.of(libro));
        when(solicitudRepository.existsByUsuarioAndLibro(usuario, libro)).thenReturn(false);

        // Llamar al método del servicio
        Solicitud solicitud = solicitudService.realizarSolicitud(usuarioId, libroId);

        // Verificar que el método save del repository fue llamado
        verify(solicitudRepository, times(1)).save(any());

        // Verificar que el libro cambió a estado RESERVADO
        assertEquals(Estado.RESERVADO, libro.getEstado());
        // Verificar que la solicitud se creó correctamente
        assertNotNull(solicitud);
        assertEquals(usuario, solicitud.getUsuario());
        assertEquals(libro, solicitud.getLibro());
        assertNotNull(solicitud.getFechaSolicitada());
    }
}
