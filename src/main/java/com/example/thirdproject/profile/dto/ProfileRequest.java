package com.example.thirdproject.profile.dto;

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
}
