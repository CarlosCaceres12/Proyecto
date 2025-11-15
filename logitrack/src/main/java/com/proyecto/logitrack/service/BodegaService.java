package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Bodega;
import java.util.List;

public interface BodegaService {
    Bodega save(Bodega bodega);
    Bodega findById(Long id);
    List<Bodega> findAll();
    void delete(Long id);
    List<Bodega> listar();
    Bodega obtener(Long id);
    Bodega guardar(Bodega bodega);
    Bodega actualizar(Long id, Bodega bodega);
    void eliminar(Long id);
}
