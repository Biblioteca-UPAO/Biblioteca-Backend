package com.upao.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "editoriales")
@Entity(name = "Editorial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "editorialId")
public class Editorial {

    /**
     * Identificador único de la editorial.
     */

    @Id
    @Column(name = "editorial_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long editorialId;

    /**
     * Nombre de la editorial.
     */

    private String nombre;

    /**
     * Ciudad en la que se ubica la editorial.
     */

    private String ciudad;
}
