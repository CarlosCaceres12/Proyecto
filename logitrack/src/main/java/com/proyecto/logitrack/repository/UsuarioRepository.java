package com.proyecto.logitrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.logitrack.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
