package com.whilabel_renewal.whilabel_backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtTokenManager {

    @Value("${jwt.secret}")
    private String secret;
    private final Key secretKey;

    public JwtTokenManager() {
        byte[] keyBytes = Base64.getDecoder().decode("yPfZyQngsFpMklbL4rQfTwGhk2KmFfxdfsasdfasdfasdasdfagasdfasdfasdfasdfasdf");
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(Long userId) {
        return createToken(new HashMap<>(), userId);
    }

    private String createToken(Map<String, Objects> claims, Long userId) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

}
