package com.jira.ticketing.service;

import com.jira.ticketing.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtService {


    private final JwtConfig jwtConfig;

    @Autowired
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(String username) {
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1);
        return Jwts.builder()
                .subject(username)
                .expiration(Date.from(expirationTime.atZone(java.time.ZoneId.systemDefault()).toInstant()))
                .signWith(buildSignature(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey buildSignature() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
