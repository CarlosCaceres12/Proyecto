package com.proyecto.logitrack.service;

import com.proyecto.logitrack.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Usuario findByEmail(String email);
    List<Usuario> findAll();
    Optional<Usuario> buscarPorEmail(String email);
    Usuario registrar(Usuario usuario);
    List<Usuario> getAllUsers();
    Optional<Usuario> getUserById(Long id);
    Optional<Usuario> getUserByUsername(String username);
    void deleteUser(Long id);
}
