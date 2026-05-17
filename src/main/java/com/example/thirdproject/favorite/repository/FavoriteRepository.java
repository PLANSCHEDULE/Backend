package com.example.thirdproject.favorite.repository;

import com.example.thirdproject.favorite.entity.Favorite;
import com.example.thirdproject.post.entity.PostTemplate;
import com.example.thirdproject.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 특정 유저가 좋아요를 눌렀는지 여부
    Optional<Favorite> findByProfileAndPostTemplate(Profile profile, PostTemplate postTemplate);
}
