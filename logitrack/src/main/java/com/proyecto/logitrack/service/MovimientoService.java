package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Movimiento;
import java.util.List;

public interface MovimientoService {
    Movimiento save(Movimiento movimiento);
    Movimiento findById(Long id);
    List<Movimiento> findAll();
    void delete(Long id);
    List<Movimiento> listar();
    Movimiento obtener(Long id);
    Movimiento guardar(Movimiento movimiento);
    void eliminar(Long id);
    java.util.List<Movimiento> findByFechaBetween(java.time.LocalDateTime desde, java.time.LocalDateTime hasta);
}
