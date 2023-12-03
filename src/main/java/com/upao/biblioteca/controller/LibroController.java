package com.upao.biblioteca.controller;

import com.upao.biblioteca.domain.dto.libroDto.DatosListadoLibro;
import com.upao.biblioteca.domain.dto.libroDto.DatosRegistroLibro;
import com.upao.biblioteca.domain.entity.Autor;
import com.upao.biblioteca.domain.entity.Libro;
import com.upao.biblioteca.domain.service.AutorService;
import com.upao.biblioteca.domain.service.LibroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Controlador REST para operaciones relacionadas con libros.
 * Proporciona endpoints para listar, crear y actualizar información de libros.
 * Utiliza {@link LibroService} y {@link AutorService} para realizar operaciones de negocio.
 */

@RestController
@RequestMapping("/libro")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LibroController {
    @Autowired
    private final LibroService libroService;

    @Autowired
    private final AutorService autorService;

    public static String uploadDirectory =
            System.getProperty("user.dir") + "/src/main/resources/static/images";

    /**
     * Lista los libros disponibles en el catálogo.
     *
     * @param pageable Configuración de paginación para la lista de libros.
     * @return Una página de libros con datos reducidos para listados.
     */

    @GetMapping("catalogo-existente")
    public ResponseEntity<Page<DatosListadoLibro>> listarLibros(@PageableDefault(size = 8) Pageable pageable) {
       Page<DatosListadoLibro> page = libroService.obtenerLibros(pageable)
               .map(libro -> new DatosListadoLibro(
                       libro.getTitulo(),
                       libro.getEstado(),
                       libro.getPortada(),
                       libro.getAutor().getAutorId(),
                       libro.getAutor().getNombre()
               ));
       return ResponseEntity.ok(page);
    }

    /**
     * Crea un nuevo libro y lo agrega al catálogo.
     *
     * @param datosRegistroLibro DTO con los datos del libro a crear.
     * @param uriComponentsBuilder Constructor de URI para la respuesta.
     * @param file Archivo de imagen representando la portada del libro.
     * @return Una ResponseEntity con la información del libro creado.
     * @throws IOException Si ocurre un error al guardar la imagen de la portada.
     * @throws ResponseStatusException Si no se encuentra el autor especificado.
     */

    @PostMapping("/crear-libro")
    @Transactional
    public ResponseEntity<DatosRegistroLibro> agregarLibro(@RequestBody @Valid DatosRegistroLibro datosRegistroLibro,
                                                             UriComponentsBuilder uriComponentsBuilder, @RequestParam("image") MultipartFile file) throws IOException {

        Optional<Autor> autorOpt = autorService.buscarPorNombre(datosRegistroLibro.autorNombre());

        if (!autorOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado");
        }

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());

        Autor autor = autorOpt.get();

        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(datosRegistroLibro.titulo());
        nuevoLibro.setEstado(datosRegistroLibro.estado());
        nuevoLibro.setPortada(originalFilename);
        nuevoLibro.setAutor(autor);

        Libro libroGuardado = libroService.agregarLibro(nuevoLibro);
        URI location = uriComponentsBuilder.path("/libro/{id}").buildAndExpand(libroGuardado.getLibroId()).toUri();
        return ResponseEntity.created(location).body(new DatosRegistroLibro(
                libroGuardado.getTitulo(),
                libroGuardado.getEstado(),
                libroGuardado.getPortada(),
                libroGuardado.getAutor().getNombre()
        ));
    }
}
