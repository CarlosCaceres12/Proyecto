package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Movimiento;
import com.proyecto.logitrack.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> listar() {
        return movimientoService.listar();
    }

    @GetMapping("/rango")
    public List<Movimiento> movimientosPorRango(@RequestParam String desde, @RequestParam String hasta) {
        java.time.LocalDateTime d = java.time.LocalDateTime.parse(desde);
        java.time.LocalDateTime h = java.time.LocalDateTime.parse(hasta);
        return movimientoService.findByFechaBetween(d, h);
    }

    @GetMapping("/{id}")
    public Movimiento obtener(@PathVariable Long id) {
        return movimientoService.obtener(id);
    }

    @PostMapping
    public Movimiento guardar(@RequestBody Movimiento movimiento) {
        return movimientoService.guardar(movimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
