package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.dto.ProfileUpdate;
import com.example.thirdproject.profile.entity.Profile;

public interface ProfileService {

    // 프로필 생성
    ProfileResponse createProfile(Long userId, ProfileRequest profileRequest);

    // 프로필 수정
    ProfileResponse updateProfile(Long userId, ProfileUpdate profileUpdate);

    // 프로필 삭제

    // 프로필 조회
    ProfileResponse getProfile(Long userId);

    // 사용자 Id를 통한 프로필 조회 (template 용)
    Profile findProfileByUserId(Long userId);
}
