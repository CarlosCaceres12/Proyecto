package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Movimiento;
import com.proyecto.logitrack.repository.MovimientoRepository;
import com.proyecto.logitrack.service.MovimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento findById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> listar() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento obtener(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    @Override
    public Movimiento guardar(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public void eliminar(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public java.util.List<Movimiento> findByFechaBetween(java.time.LocalDateTime desde, java.time.LocalDateTime hasta) {
        return movimientoRepository.findByFechaBetween(desde, hasta);
    }
}
