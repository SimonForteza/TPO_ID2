package com.example.id2.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private String secretKey = "WFJ#$%^)!*@#234-7850-231123FERGWEFRFG!";
    private long jwtExpiration = 100000000;

    public String generateToken(UserDetails userDetails, String email, String dni, String role) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("email", email)
                .claim("dni", dni)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractClaim(token, Claims::getSubject).equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public String extractDni(String token) {
        return extractClaim(token, claims -> claims.get("dni", String.class));
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}