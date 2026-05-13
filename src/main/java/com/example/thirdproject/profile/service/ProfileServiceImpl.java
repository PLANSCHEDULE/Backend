package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequestDto;
import com.example.thirdproject.profile.dto.ProfileUpdateDto;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.repository.ProfileRepository;
import com.example.thirdproject.user.entity.User;
import com.example.thirdproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createProfile(Long userId, ProfileRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (profileRepository.existsByUserId(userId)) {
            throw new IllegalStateException("이미 생성된 프로필이 있습니다.");

        }
        // 핸들 체크는 api 분리해야될 듯. 그럼 이메일 중복도 중복 체크는 시간되면 다 api 분리해보는걸로 해야될 듯

        Profile profile = Profile.builder()
                .user(user)
                .handle(requestDto.getHandle()).
                nickname(requestDto.getNickname())
                .bio(requestDto.getBio())
                .build();

        profileRepository.save(profile);



    }

    @Override
    @Transactional
    public void updateProfile(Long userId, ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 프로필이 없습니다."));

        profile.update(profileUpdateDto);
    }

}
