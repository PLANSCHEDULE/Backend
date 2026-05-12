package com.example.thirdproject.auth.service;

import com.example.thirdproject.auth.dto.LoginRequest;
import com.example.thirdproject.auth.dto.LoginResponse;
import com.example.thirdproject.auth.dto.SignUpRequest;
import com.example.thirdproject.auth.dto.SignUpResponse;
import com.example.thirdproject.global.security.jwt.JwtTokenProvider;
import com.example.thirdproject.user.entity.User;
import com.example.thirdproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public SignUpResponse signup(SignUpRequest request) {
        // exception handler 만들어야됨
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        User savedUser = userRepository.save(user);

        return SignUpResponse.from(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        String refreshToken = jwtTokenProvider.refreshToken(user.getEmail());

        // redis에 refreshtoken 저장
        refreshTokenService.saveRefreshToken(
                user.getEmail(),
                refreshToken,
                jwtTokenProvider.getRefreshTokenExpiration()
        );

        // response 변수명이 왜 중복 되는거지?
        LoginResponse response = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }

    @Override
    @Transactional
    public LoginResponse refresh(String refreshToken) {
        // refresh는 Permitall이어서 이쪽은 exception 따로 잡아줘야 됨(매번 까먹는데 제발 기억하자..)
        if(!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않거나 만료된 Refresh Token 입니다.");
        }

        String email = jwtTokenProvider.getEmail(refreshToken);

        if(!refreshTokenService.isRefreshTokenMatch(email, refreshToken)) {
            throw new IllegalArgumentException("RefreshToken이 일치하지 않습니다.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        String newAccessToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        String newRefreshToken = jwtTokenProvider.refreshToken(user.getEmail());

        // redis에 업데이트
        refreshTokenService.saveRefreshToken(
                user.getEmail(),
                newRefreshToken,
                jwtTokenProvider.getRefreshTokenExpiration()
        );

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();


    }




}
