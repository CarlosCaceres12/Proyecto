package com.proyecto.logitrack.controller;

import com.proyecto.logitrack.model.Usuario;
import com.proyecto.logitrack.security.JwtUtil;
import com.proyecto.logitrack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        Usuario u = usuarioService.buscarPorEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!u.getPassword().equals(usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwtUtil.generateToken(u.getEmail(), u.getRol().getNombre()));
        response.put("usuario", u);

        return ResponseEntity.ok(response);
    }
}
