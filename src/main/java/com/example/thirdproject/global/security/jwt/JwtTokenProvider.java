package com.example.thirdproject.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtTokenProvider(
        @Value("${spring.jwt.secret}") String secret,
        @Value("${spring.jwt.access-token-expiration}") long accessTokenExpiration,
        @Value("${spring.jwt.refresh-token-expiration}") long refreshTokenExpiration

    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    // access 토큰 생성
    public String createToken(String email, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpiration);

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }

    // refresh 토큰 생성
    public String refreshToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpiration);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }



    // refresh 만료 시간



    // token 사용자 추출
    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    // 토큰 유효성 검증


    //인증 객체 생성


}
