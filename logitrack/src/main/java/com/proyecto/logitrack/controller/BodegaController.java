package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Bodega;
import com.proyecto.logitrack.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public List<Bodega> listar() {
        return bodegaService.listar();
    }

    @GetMapping("/{id}")
    public Bodega obtener(@PathVariable Long id) {
        return bodegaService.obtener(id);
    }

    @PostMapping
    public Bodega guardar(@RequestBody Bodega bodega) {
        return bodegaService.guardar(bodega);
    }

    @PutMapping("/{id}")
    public Bodega actualizar(@PathVariable Long id, @RequestBody Bodega bodega) {
        return bodegaService.actualizar(id, bodega);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
