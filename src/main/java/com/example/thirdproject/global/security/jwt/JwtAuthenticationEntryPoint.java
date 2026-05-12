package com.example.thirdproject.global.security.jwt;

import com.example.thirdproject.global.security.AuthErrorCode;
import com.example.thirdproject.global.security.AuthErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Bean
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException {

        // 필터가 넘겨준 에러 코드 받기
        String exceptionCode = (String) request.getAttribute("exception");

        // 에러 코드 (enum)
        AuthErrorCode errorCode = AuthErrorCode.fromCode(exceptionCode);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // AuthErrorResponse Dto
        AuthErrorResponse authErrorResponse = AuthErrorResponse.message(errorCode, request.getRequestURI());

        objectMapper.writeValue(response.getWriter(), authErrorResponse);
    }

}
