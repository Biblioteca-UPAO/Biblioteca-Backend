package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa un libro en el contexto de una biblioteca.
 * Cada libro tiene un identificador único, título, estado, portada y está asociado a un autor.
 * Esta entidad es parte integral del modelo de datos de la biblioteca, vinculándose con la entidad Autor.
 */

@Table(name = "libros")
@Entity(name = "Libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "libroId")
public class Libro {

    /**
     * Identificador único del libro.
     */

    @Id
    @Column(name = "libro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libroId;

    /**
     * Título del libro.
     */

    private String titulo;

    /**
     * Estado actual del libro (por ejemplo: disponible, prestado).
     * Se utiliza un tipo enumerado para representar los diferentes estados.
     */

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /**
     * Imagen de la portada del libro.
     * Se almacena como un objeto Lob (Large Object) debido a su potencial tamaño grande.
     */

    @Column(nullable = false)
    @Lob
    private String portada;

    /**
     * Autor asociado con el libro.
     * Se establece una relación de muchos a uno con la entidad Autor.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id_fk")
    private Autor autor;

}
