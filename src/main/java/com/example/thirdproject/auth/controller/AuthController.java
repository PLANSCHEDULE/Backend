package com.example.thirdproject.auth.controller;

import com.example.thirdproject.auth.dto.SignUpRequest;
import com.example.thirdproject.auth.dto.SignUpResponse;
import com.example.thirdproject.auth.service.AuthService;
import com.example.thirdproject.global.commonResponse.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponse>> signup(
            @RequestBody SignUpRequest signUpRequest,
            HttpServletRequest request
            ) {

        SignUpResponse data = authService.signup(signUpRequest);

        ApiResponse<SignUpResponse> response = ApiResponse.created(
                "회원가입이 성공적으로 완료되었습니다.",
                data,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
