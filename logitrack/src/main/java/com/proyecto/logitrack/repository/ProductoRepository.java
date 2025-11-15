package com.proyecto.logitrack.repository;

import com.proyecto.logitrack.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	java.util.List<com.proyecto.logitrack.model.Producto> findByStockLessThan(Integer stock);

}
