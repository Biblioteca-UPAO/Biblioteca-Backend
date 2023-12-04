package com.upao.biblioteca.controller;

import com.upao.biblioteca.domain.entity.Solicitud;
import com.upao.biblioteca.domain.service.SolicitudService;
import com.upao.biblioteca.infra.repository.LibroRepository;
import com.upao.biblioteca.infra.repository.SolicitudRepository;
import com.upao.biblioteca.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/realizar-solicitud")
    public ResponseEntity<Solicitud> realizarSolicitud(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        try {
            Solicitud solicitud = solicitudService.realizarSolicitud(usuarioId, libroId);
            return new ResponseEntity<>(solicitud, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ver-solicitudes")
    public ResponseEntity<?> verSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.verSolicitudes();

        if (solicitudes.isEmpty()) {
            return new ResponseEntity<>("Aún no se realiza ninguna solicitud", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    public ResponseEntity<List<Solicitud>> listarTodasLasSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }
}
