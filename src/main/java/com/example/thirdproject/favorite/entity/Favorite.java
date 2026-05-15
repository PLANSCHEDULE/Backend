package com.example.thirdproject.favorite.entity;

import com.example.thirdproject.community.entity.PostTemplate;
import com.example.thirdproject.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "favorites",
        uniqueConstraints = {
                // 중복 입력을 차단하기 위해 uniqueConstraint를 걸어줄 수 있음
                @UniqueConstraint(
                        name = "uk_profile_post_template",
                        columnNames = {"profile_id", "post_template_id"}
                )

        }
)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_template_id", nullable = false)
    private PostTemplate postTemplate;

    @Builder
    public Favorite(Profile profile, PostTemplate postTemplate) {
        this.profile = profile;
        this.postTemplate = postTemplate;
    }


}
