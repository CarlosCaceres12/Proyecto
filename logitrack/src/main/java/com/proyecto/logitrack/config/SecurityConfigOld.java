package com.proyecto.logitrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Esta clase ha sido reemplazada por la del paquete security
// Se mantiene para referencia histórica
public class SecurityConfigOld {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas con Postman o Insomnia
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite todas las peticiones sin login
            );
        return http.build();
    }
}
