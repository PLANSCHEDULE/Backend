package com.example.thirdproject.profile.entity;

import com.example.thirdproject.global.entity.BaseTime;
import com.example.thirdproject.profile.dto.ProfileUpdateDto;
import com.example.thirdproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String handle;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Profile(User user, String handle, String nickname, String bio) {
        this.user = user;
        this.handle = handle;
        this.nickname = nickname;
        this.bio = bio;
    }

    public void update(ProfileUpdateDto dto) {
        if(dto.getNickname() != null && !dto.getNickname().isBlank()) {
            this.nickname = dto.getNickname();
        }
        // 공백일 수도 있음
        if(dto.getBio() != null) {
            this.bio = dto.getBio();
        }
    }
}
