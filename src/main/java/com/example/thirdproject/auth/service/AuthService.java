package com.example.thirdproject.auth.service;


import com.example.thirdproject.auth.dto.SignUpRequest;
import com.example.thirdproject.auth.dto.SignUpResponse;

public interface AuthService {
    // 회원가입
    SignUpResponse signup(SignUpRequest request);
}
