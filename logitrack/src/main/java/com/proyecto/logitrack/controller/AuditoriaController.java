package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Auditoria;
import com.proyecto.logitrack.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<Auditoria> listar() {
        return auditoriaService.listar();
    }

    @GetMapping("/{id}")
    public Auditoria obtener(@PathVariable Long id) {
        return auditoriaService.obtener(id);
    }
}
