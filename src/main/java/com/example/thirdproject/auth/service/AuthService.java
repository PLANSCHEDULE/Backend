package com.example.thirdproject.auth.service;


import com.example.thirdproject.auth.dto.LoginRequest;
import com.example.thirdproject.auth.dto.LoginResponse;
import com.example.thirdproject.auth.dto.SignUpRequest;
import com.example.thirdproject.auth.dto.SignUpResponse;

public interface AuthService {
    // 회원가입
    SignUpResponse signup(SignUpRequest request);

    // 로그인
    LoginResponse login(LoginRequest request);

    // accessToken 재발급 (Refresh)
    LoginResponse refresh(String refreshToken);
}
