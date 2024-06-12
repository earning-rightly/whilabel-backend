package com.whilabel_renewal.whilabel_backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class JwtTokenManager {

    @Value("${jwt.secret}")
    private Key secret;


    public String generateToken(Long userId) {
        return createToken(new HashMap<>(), userId);
    }

    private String createToken(Map<String, Objects> claims, Long userId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(secret,SignatureAlgorithm.HS256)
                .compact();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

}
