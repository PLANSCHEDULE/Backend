package com.example.thirdproject.profile.service;

import com.example.thirdproject.profile.dto.ProfileRequest;
import com.example.thirdproject.profile.dto.ProfileResponse;
import com.example.thirdproject.profile.dto.ProfileUpdate;
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
    public ProfileResponse createProfile(Long userId, ProfileRequest profileRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (profileRepository.existsByUserId(userId)) {
            throw new IllegalStateException("이미 생성된 프로필이 있습니다.");

        }
        // 핸들 체크는 api 분리해야될 듯. 그럼 이메일 중복도 중복 체크는 시간되면 다 api 분리해보는걸로 해야될 듯
        // 생각해보니 api를 따로 만든다쳐도 여기서도 한 번 더 확인해야됨
        if (profileRepository.existsByHandle(profileRequest.getHandle())) {
            throw new IllegalArgumentException("이미 사용 중인 핸들입니다.");
        }

        Profile profile = profileRequest.saveProfile(user);

        Profile saveProfile = profileRepository.save(profile);

        return ProfileResponse.from(saveProfile);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(Long userId, ProfileUpdate profileUpdate) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 프로필이 없습니다."));

        profile.update(profileUpdate);

        return ProfileResponse.from(profile);
    }

    // 프로필 조회
    @Override
    @Transactional
    public ProfileResponse getProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않습니다."));

        return ProfileResponse.from(profile);
    }



    // 회원탈퇴 기능도 만들어야됨.. 회원탈퇴할 때 같이 사라지게 하면 될 듯 프로필 삭제는

}
