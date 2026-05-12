package com.example.thirdproject.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // redis 데이터를 문자열 형태로 변환하는 설정
    // 이 설정을 안하면 기본 직렬화 방식이 달라 터미널에서 읽을 수 없고, 디버깅하기 힘듬

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        // RedisConnectionFactory: redis와 연결을 맺는 연결관리자이고 스프링 부트와 Lettuce라는 고성능 클라이언트를 사용해 redis에 접속
        // RedisTemplate: Redis 데이터를 읽고 쓰기 위한 메인 도구!

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 키 직렬화
        template.setKeySerializer(new StringRedisSerializer());
        // value 직렬화
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
