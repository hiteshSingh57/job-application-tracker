package com.example.job_tracker_application.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey  key = Keys.hmacShaKeyFor(
            "ye-bhout-lamba-secret-key-hai-kam-se-kam-32-character".getBytes()
    );

    private  final long EXPIRATION = 1000 * 60 * 60 * 10;

    public String generatedToken(String email) {
        return  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractEmail(token);
            return true;
        }
        catch (Exception e) {
            System.out.println("Jwt Error" + e.getMessage());
            return false;
        }
    }
}
