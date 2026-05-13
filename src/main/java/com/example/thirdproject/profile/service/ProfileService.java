package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequestDto;

public interface ProfileService {

    // 프로필 생성
    void createProfile(Long userId, ProfileRequestDto requestDto);

    // 프로필 수정

    // 프로필 삭제
}
