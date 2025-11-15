package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Bodega;
import com.proyecto.logitrack.model.Producto;
import com.proyecto.logitrack.model.Movimiento;
import com.proyecto.logitrack.repository.BodegaRepository;
import com.proyecto.logitrack.repository.ProductoRepository;
import com.proyecto.logitrack.repository.MovimientoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;
    private final MovimientoRepository movimientoRepository;

    public ReportController(BodegaRepository bodegaRepository,
                            ProductoRepository productoRepository,
                            MovimientoRepository movimientoRepository) {
        this.bodegaRepository = bodegaRepository;
        this.productoRepository = productoRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @GetMapping("/resumen")
    public Map<String, Object> resumen(@RequestParam(defaultValue = "5") int top) {
        Map<String, Object> out = new HashMap<>();

        // stock total por bodega
        List<Bodega> bodegas = bodegaRepository.findAll();
        List<Producto> productos = productoRepository.findAll();

        Map<String, Integer> stockPorBodega = new HashMap<>();
        for (Bodega b : bodegas) {
            int sum = productos.stream()
                    .filter(p -> p.getBodega() != null && Objects.equals(p.getBodega().getId(), b.getId()))
                    .mapToInt(p -> p.getStock() == null ? 0 : p.getStock())
                    .sum();
            stockPorBodega.put(b.getNombre(), sum);
        }

        // productos mas movidos
        List<Movimiento> movimientos = movimientoRepository.findAll();
        Map<String, Integer> movimientosPorProducto = new HashMap<>();
        for (Movimiento m : movimientos) {
            if (m.getProducto() != null) {
                String prodName = m.getProducto().getNombre();
                movimientosPorProducto.put(prodName,
                        movimientosPorProducto.getOrDefault(prodName, 0) + (m.getCantidad() == null ? 0 : m.getCantidad()));
            }
        }

        List<Map.Entry<String, Integer>> topList = movimientosPorProducto.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(top)
                .collect(Collectors.toList());

        out.put("stockPorBodega", stockPorBodega);
        out.put("productosMasMovidos", topList);

        return out;
    }
}
