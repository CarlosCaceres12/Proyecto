package com.proyecto.logitrack.security;

import com.proyecto.logitrack.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String email = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception ignored) {}
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                if (token != null && jwtUtil.isTokenValid(token)) {
                    var usuarioOpt = usuarioService.buscarPorEmail(email);
                    if (usuarioOpt.isPresent()) {
                        var usuario = usuarioOpt.get();

                        String rolNombre = null;
                        if (usuario.getRol() != null && usuario.getRol().getNombre() != null) {
                            rolNombre = usuario.getRol().getNombre();
                        } else {
                            try {
                                rolNombre = jwtUtil.extractRol(token);
                            } catch (Exception ignored) {
                            }
                        }

                        if (rolNombre == null) {
                            rolNombre = "EMPLEADO"; // valor por defecto
                        }

                        User user = new User(
                                usuario.getEmail(),
                                usuario.getPassword(),
                                rolNombre.equalsIgnoreCase("ADMIN")
                                        ? java.util.List.of(() -> "ROLE_ADMIN")
                                        : java.util.List.of(() -> "ROLE_EMPLEADO")
                        );

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        user, null, user.getAuthorities()
                                );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception ex) {
                // No propagar excepción desde el filtro; dejar que la request siga sin autenticación
            }
        }

        filterChain.doFilter(request, response);
    }
}
