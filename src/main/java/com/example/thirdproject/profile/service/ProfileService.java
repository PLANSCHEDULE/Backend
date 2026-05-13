package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.dto.ProfileUpdate;

public interface ProfileService {

    // 프로필 생성
    ProfileResponse createProfile(Long userId, ProfileRequest profileRequest);

    // 프로필 수정
    ProfileResponse updateProfile(Long userId, ProfileUpdate profileUpdate);

    // 프로필 삭제

    // 프로필 조회
    ProfileResponse getProfile(Long userId);
}
