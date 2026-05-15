package com.example.thirdproject.profile.dto;

import com.example.thirdproject.profile.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {
    private String userId;
    private String handle;
    private String nickname;
    private String bio;

    public static ProfileResponse from(Profile profile) {
        return ProfileResponse.builder()
                .userId(profile.getUser().getUuid())
                .handle(profile.getHandle())
                .nickname(profile.getNickname())
                .bio(profile.getBio())
                .build();
    }
}
