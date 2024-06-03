package com.example.Friend.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String SECRET_KEY = "your_secret_key_here"; // Utilisez une clé secrète plus sécurisée
    private final long VALIDITY_IN_MILLISECONDS = 3600000; // 1 heure

    public String generateToken(String email) {
        System.out.println("Username: " + email);
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + VALIDITY_IN_MILLISECONDS);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        System.out.println("Token received : " + token);
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("SignatureException");
        } catch (MalformedJwtException ex) {
            System.out.println("MalformedJwtException");
        } catch (ExpiredJwtException ex) {
            System.out.println("ExpiredJwtException");
        } catch (UnsupportedJwtException ex) {
            System.out.println("UnsupportedJwtException");
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgumentException");
        }
        return false;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
