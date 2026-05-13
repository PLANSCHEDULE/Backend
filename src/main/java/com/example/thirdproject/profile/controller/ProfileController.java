package com.example.thirdproject.profile.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> createProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ProfileRequest profileRequest,
            HttpServletRequest request
            ) {

        ProfileResponse response = profileService.createProfile(userDetails.getUser().getId(), profileRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created("프로필 생성 완료", response, request.getRequestURI())
        );

    }
}
