package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Bodega;
import com.proyecto.logitrack.repository.BodegaRepository;
import com.proyecto.logitrack.service.BodegaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;

    public BodegaServiceImpl(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    @Override
    public Bodega save(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    @Override
    public Bodega findById(Long id) {
        return bodegaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bodega> findAll() {
        return bodegaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        bodegaRepository.deleteById(id);
    }

    @Override
    public List<Bodega> listar() {
        return bodegaRepository.findAll();
    }

    @Override
    public Bodega obtener(Long id) {
        return bodegaRepository.findById(id).orElse(null);
    }

    @Override
    public Bodega guardar(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    @Override
    public Bodega actualizar(Long id, Bodega bodega) {
        bodega.setId(id);
        return bodegaRepository.save(bodega);
    }

    @Override
    public void eliminar(Long id) {
        bodegaRepository.deleteById(id);
    }
}
