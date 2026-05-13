package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.dto.ProfileUpdateDto;

public interface ProfileService {

    // 프로필 생성
    void createProfile(Long userId, ProfileRequest requestDto);

    // 프로필 수정
    ProfileResponse updateProfile(Long userId, ProfileUpdateDto profileUpdateDto);

    // 프로필 삭제
}
