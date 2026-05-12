package com.example.thirdproject.global.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AuthErrorCode {
    EXPIRED_ACCESS_TOKEN("EXPIRED_ACCESS_TOKEN", "액세스 토큰이 만료되었습니다. 리프레시 토큰을 사용해 재발급하세요."),
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    UNSUPPORTED_TOKEN("UNSUPPORTED_TOKEN", "지원하지 않는 JWT 토큰입니다."),
    ILLEGAL_TOKEN("ILLEGAL_TOKEN", "토큰이 비거나 잘못된 인자(null)입니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", "유저가 없습니다."),
    UNAUTHORIZED("UNAUTHORIZED", "인증이 필요한 서비스입니다.");

    private final String code;
    private final String message;

    public static AuthErrorCode fromCode(String code) {
        return Arrays.stream(values())
                .filter(errorCode -> errorCode.code.equals(code))
                .findFirst()
                .orElse(UNAUTHORIZED);
    }
}
