package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Rol;
import java.util.List;

public interface RolService {
    List<Rol> findAll();
    Rol findById(Long id);
    Rol save(Rol rol);
}
