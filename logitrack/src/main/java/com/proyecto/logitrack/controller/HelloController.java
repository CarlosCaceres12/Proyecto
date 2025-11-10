package com.proyecto.logitrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    public HelloController() {
        System.out.println("✅ HelloController cargado correctamente");
    }

    // Endpoint para /hello y /
    @GetMapping({"/hello", "/"})
    public String hello() {
        return "<html>" +
               "<body style='font-family: Arial; text-align:center; margin-top:50px;'>" +
               "<h1>🚀 Servidor Spring Boot funcionando correctamente!</h1>" +
               "<p>Puerto: 35099</p>" +
               "</body>" +
               "</html>";
    }
}
