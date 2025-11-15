package com.proyecto.logitrack.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.logitrack.model.Auditoria;
import com.proyecto.logitrack.model.Usuario;
import com.proyecto.logitrack.service.AuditoriaService;
import com.proyecto.logitrack.service.UsuarioService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditoriaAspect {

    private final AuditoriaService auditoriaService;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuditoriaAspect(AuditoriaService auditoriaService, UsuarioService usuarioService) {
        this.auditoriaService = auditoriaService;
        this.usuarioService = usuarioService;
    }

    @AfterReturning(pointcut = "execution(public * com.proyecto.logitrack.controller..*(..))", returning = "result")
    public void afterController(JoinPoint joinPoint, Object result) {
        try {
            MethodSignature sig = (MethodSignature) joinPoint.getSignature();
            Method method = sig.getMethod();

            String accion = null;
            if (method.isAnnotationPresent(org.springframework.web.bind.annotation.PostMapping.class)) {
                accion = "INSERT";
            } else if (method.isAnnotationPresent(org.springframework.web.bind.annotation.PutMapping.class)) {
                accion = "UPDATE";
            } else if (method.isAnnotationPresent(org.springframework.web.bind.annotation.DeleteMapping.class)) {
                accion = "DELETE";
            }

            if (accion == null) return; // sólo auditar cambios

            String detalle = "";
            try {
                Object[] args = joinPoint.getArgs();
                if (args != null && args.length > 0) {
                    detalle = objectMapper.writeValueAsString(args[0]);
                }
            } catch (Exception e) {
                detalle = "(no se pudo serializar detalle)";
            }

            // intentar obtener usuario desde el contexto
            Usuario usuario = null;
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
                    usuario = usuarioService.buscarPorEmail(auth.getName()).orElse(null);
                }
            } catch (Exception ignored) {}

            Auditoria a = new Auditoria();
            a.setAccion(accion);
            a.setFecha(LocalDateTime.now());
            a.setDetalle(detalle);
            a.setUsuario(usuario);

            auditoriaService.save(a);
        } catch (Exception ex) {
            // no bloquear la ejecución por errores de auditoría
        }
    }
}
