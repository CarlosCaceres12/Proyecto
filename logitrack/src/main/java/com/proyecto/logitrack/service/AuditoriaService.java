package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Auditoria;
import java.util.List;

public interface AuditoriaService {
    Auditoria save(Auditoria auditoria);
    List<Auditoria> findAll();
    List<Auditoria> listar();
    Auditoria obtener(Long id);
    void delete(Long id);
}
