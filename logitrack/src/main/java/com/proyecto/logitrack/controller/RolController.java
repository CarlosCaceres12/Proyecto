package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Rol;
import com.proyecto.logitrack.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol saved = rolService.save(rol);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> list() {
        return ResponseEntity.ok(rolService.findAll());
    }
}
