package com.example.thirdproject.global.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;

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
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .error("Unauthorized")
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(path)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}
