package com.proyecto.logitrack.model;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private Integer stock;
    private Double precio;

    // getters y setters
    // ...
}
