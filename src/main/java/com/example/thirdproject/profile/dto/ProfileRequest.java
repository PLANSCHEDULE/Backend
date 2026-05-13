package com.example.thirdproject.profile.dto;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String handle;
    private String nickname;
    private String bio;

    public Profile saveProfile(User user) {
        return Profile.builder()
                .user(user)
                .handle(this.handle)
                .nickname(this.nickname)
                .bio(this.bio)
                .build();
    }
}
