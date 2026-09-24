package com.ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // Llave segura de 256 bits para firmar el token
    private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira en 10 horas
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token, String username) {
        return extraerUsername(token).equals(username) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}