package com.example.thirdproject.profile.repository;

import com.example.thirdproject.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // 유저 프로필 존재 확인
    boolean existsByUserId(Long userId);

    // 핸들 중복 여부 확인
    boolean existsByHandle(String handle);
}
