package com.example.thirdproject.auth.service;

import com.example.thirdproject.auth.dto.SignUpRequest;
import com.example.thirdproject.auth.dto.SignUpResponse;
import com.example.thirdproject.user.entity.User;
import com.example.thirdproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
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



}
