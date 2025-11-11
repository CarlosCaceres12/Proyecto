package com.proyecto.logitrack.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Bodega {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String encargado;
    private Instant createdAt = Instant.now();

    // getters y setters
    // ...
}
