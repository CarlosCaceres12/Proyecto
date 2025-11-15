package com.proyecto.logitrack.config;

import com.proyecto.logitrack.model.Usuario;
import com.proyecto.logitrack.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (Usuario u : usuarioRepository.findAll()) {
            String pwd = u.getPassword();
            if (pwd != null && !pwd.startsWith("$2")) { // no está hasheada
                u.setPassword(passwordEncoder.encode(pwd));
                usuarioRepository.save(u);
            }
        }
    }
}
