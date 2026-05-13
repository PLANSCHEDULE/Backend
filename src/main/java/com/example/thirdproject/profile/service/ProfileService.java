package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequestDto;
import com.example.thirdproject.profile.dto.ProfileUpdateDto;

public interface ProfileService {

    // 프로필 생성
    void createProfile(Long userId, ProfileRequestDto requestDto);

    // 프로필 수정
    void updateProfile(Long userId, ProfileUpdateDto profileUpdateDto);

    // 프로필 삭제
}
