package com.proyecto.logitrack.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.proyecto.logitrack.model.Bodega;
import com.proyecto.logitrack.repository.BodegaRepository;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    private final BodegaRepository repo;

    public BodegaController(BodegaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Bodega> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Bodega crear(@RequestBody Bodega bodega) {
        return repo.save(bodega);
    }
}
    