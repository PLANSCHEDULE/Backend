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

    // redis에서 이메일을 키로 사용해 refresh token 추출
    public String getRefreshToken(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    // refresh api를 위한 함수
    // redis에서 저장된 토큰과 일치하는지 확인
    public boolean isRefreshTokenMatch(String email, String refreshToken) {
        String storedToken = getRefreshToken(email);

        if (storedToken == null) {
            return false;
        }

        if(storedToken.equals(refreshToken)) {
            return true;
        } else {
            return false;
        }
    }
}
