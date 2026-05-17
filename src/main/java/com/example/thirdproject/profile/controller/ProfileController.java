package com.example.thirdproject.profile.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.dto.ProfileUpdate;
import com.example.thirdproject.profile.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Profile controller", description = "프로필 api")
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "프로필 생성 api")
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

    @Operation(summary = "프로필 수정 api")
    @PatchMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ProfileUpdate profileUpdate,
            HttpServletRequest request
            ) {
        ProfileResponse response = profileService.updateProfile(userDetails.getUser().getId(), profileUpdate);

        return ResponseEntity.ok(
                ApiResponse.success("프로필 수정 완료", response, request.getRequestURI())
        );

    }

    @Operation(summary = "프로필 조회 api")
    @GetMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> getMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request
    ) {
        ProfileResponse response = profileService.getProfile(userDetails.getUser().getId());

        return ResponseEntity.ok(
                ApiResponse.success("프로필 조회 성공", response, request.getRequestURI())
        );
    }
}
