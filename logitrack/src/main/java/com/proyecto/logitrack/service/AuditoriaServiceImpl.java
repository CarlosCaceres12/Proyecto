package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Auditoria;
import com.proyecto.logitrack.repository.AuditoriaRepository;
import com.proyecto.logitrack.service.AuditoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    @Override
    public Auditoria save(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    @Override
    public List<Auditoria> findAll() {
        return auditoriaRepository.findAll();
    }

    @Override
    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }

    @Override
    public Auditoria obtener(Long id) {
        return auditoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        auditoriaRepository.deleteById(id);
    }
}
