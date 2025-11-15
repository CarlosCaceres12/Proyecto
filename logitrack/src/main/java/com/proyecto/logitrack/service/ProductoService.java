package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Producto;
import java.util.List;

public interface ProductoService {
    Producto save(Producto producto);
    Producto findById(Long id);
    List<Producto> findAll();
    void delete(Long id);
    List<Producto> listar();
    Producto obtener(Long id);
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
    java.util.List<Producto> findByStockLessThan(Integer stock);
}
