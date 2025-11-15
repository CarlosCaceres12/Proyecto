package com.proyecto.logitrack.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Usa una clave >= 32 bytes. Mejor mover esto a application.properties en producción.
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("MI_CLAVE_SUPER_SECRETA_32_BYTES_MINIMOS_!!".getBytes());

    private final long EXPIRATION = 1000L * 60 * 60 * 5; // 5 horas

    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRol(String token) {
        return getClaims(token).get("rol", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims c = getClaims(token);
            return c.getExpiration().after(new Date());
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

