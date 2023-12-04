package com.upao.biblioteca.domain.service;

import com.upao.biblioteca.domain.entity.Estado;
import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.entity.Solicitud;
import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.infra.repository.LibroRepository;
import com.upao.biblioteca.infra.repository.SolicitudRepository;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public Solicitud realizarSolicitud(Long usuarioId, Long libroId) {
        validarUsuarioExistente(usuarioId);
        validarLibroExistente(libroId);

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Libro libro = libroRepository.findById(libroId).orElseThrow();

        validarSolicitudDuplicada(usuario, libro);

        // Verifica que el libro esté en estado DISPONIBLE antes de reservar
        if (libro.getEstado() != Estado.DISPONIBLE) {
            throw new IllegalStateException("No se puede realizar la solicitud. El libro no está disponible.");
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario);
        solicitud.setLibro(libro);

        // Solo asigna la fecha de solicitud
        LocalDateTime now = LocalDateTime.now();
        solicitud.setFechaSolicitada(now);

        // Cambia el estado del libro a RESERVADO
        libro.setEstado(Estado.RESERVADO);

        // Los demás campos se mantendrán como nulos por defecto

        return solicitudRepository.save(solicitud);
    }

    private void asignarFechasRecojoYMaxDevolucion(Solicitud solicitud) {
        LocalDateTime now = LocalDateTime.now();
        solicitud.setFechaRecojo(now);
        LocalDateTime fechaMaxDevolucion = now.plus(7, ChronoUnit.DAYS);
        solicitud.setFechaMaxDevolucion(fechaMaxDevolucion);
    }

    @Transactional
    public void realizarRecojo(Long solicitudId) {
        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(solicitudId);
        if (solicitudOpt.isPresent()) {
            Solicitud solicitud = solicitudOpt.get();
            if (solicitud.getFechaRecojo() != null) {
                throw new IllegalStateException("La solicitud ya ha sido recolectada anteriormente.");
            }
            LocalDateTime fechaRecojo = LocalDateTime.now();
            solicitud.setFechaRecojo(fechaRecojo);
            if (solicitud.getFechaMaxDevolucion() == null) {
                LocalDateTime fechaMaxDevolucion = fechaRecojo.plus(7, ChronoUnit.DAYS);
                solicitud.setFechaMaxDevolucion(fechaMaxDevolucion);
            }
            solicitud.getLibro().marcarNoDisponible();
            solicitudRepository.save(solicitud);
        } else {
            throw new IllegalArgumentException("Solicitud no encontrada con ID: " + solicitudId);
        }
    }

    public List<Solicitud> verSolicitudes() {
        return solicitudRepository.findAll();
    }


    private void validarUsuarioExistente(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    private void validarLibroExistente(Long libroId) {
        if (!libroRepository.existsById(libroId)) {
            throw new IllegalArgumentException("Libro no encontrado con ID: " + libroId);
        }
    }

    private void validarSolicitudDuplicada(Usuario usuario, Libro libro) {
        // Puedes implementar lógica para evitar solicitudes duplicadas
        if (solicitudRepository.existsByUsuarioAndLibro(usuario, libro)) {
            throw new IllegalStateException("Ya existe una solicitud para este usuario y libro.");
        }
    }
}
