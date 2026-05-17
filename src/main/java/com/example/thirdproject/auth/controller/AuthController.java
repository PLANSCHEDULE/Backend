package com.example.thirdproject.auth.controller;

import com.example.thirdproject.auth.dto.*;
import com.example.thirdproject.auth.service.AuthService;
import com.example.thirdproject.global.commonResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth controller", description = "인증/인가 관련 controller")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입 api")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<LoginResponse>> signup(
            @RequestBody SignUpRequest signUpRequest,
            HttpServletRequest request
            ) {

        LoginResponse data = authService.signup(signUpRequest);

        ApiResponse<LoginResponse> response = ApiResponse.created(
                "회원가입이 성공적으로 완료되었습니다.",
                data,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "로그인 api")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request
            ) {
        LoginResponse response = authService.login(loginRequest);

        return ResponseEntity.ok(
                ApiResponse.success("로그인 성공", response, request.getRequestURI())
        );
    }

    @Operation(summary = "accessToken 재발급 api")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refresh(
            @RequestBody RefreshRequest refreshRequest,
            HttpServletRequest request
            ) {
        LoginResponse response = authService.refresh(refreshRequest.getRefreshToken());

        return ResponseEntity.ok(ApiResponse.created("토큰 재발급 성공", response, request.getRequestURI()));

    }

    @Operation(summary = "로그아웃 api")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @RequestHeader("Authorization") String authorization,
            HttpServletRequest request) {
        String accessToken = authorization.substring(7);

        authService.logout(accessToken);

        return ResponseEntity.ok(
                ApiResponse.success("로그아웃 되었습니다.", request.getRequestURI())
        );

    }
}
