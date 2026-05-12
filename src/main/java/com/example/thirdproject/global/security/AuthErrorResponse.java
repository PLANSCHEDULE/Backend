package com.example.thirdproject.global.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class AuthErrorResponse {
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
    private String timestamp;

    public static AuthErrorResponse message(AuthErrorCode errorCode, String path) {
        return AuthErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Unauthorized")
                .message(errorCode.getMessage())
                .path(path)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}
