package com.proyecto.logitrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class LogitrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogitrackApplication.class, args);
    }

    // Solo para mostrar en consola el puerto donde arrancó el servidor
    @EventListener
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        System.out.println("🚀 Servidor Logitrack iniciado en el puerto: " + port);
    }
}

