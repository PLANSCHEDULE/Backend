package com.example.thirdproject.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    // refreshToken을 redis에 저장하는 서비스

    // 그럼 이거 provider에서 시간로직 다시 한 번 확인해야 될 듯
    private final RedisTemplate<String, String> redisTemplate;

    // redis 저장
    // 키: 이메일, 값: 토큰, 만료시간 설정
    public void saveRefreshToken(String email, String refreshToken, long expirationTime) {
        redisTemplate.opsForValue().set(
                email,
                refreshToken,
                Duration.ofMillis(expirationTime)
        );
    }
}
