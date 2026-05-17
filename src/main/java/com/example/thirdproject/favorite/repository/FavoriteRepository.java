package com.example.thirdproject.favorite.repository;

import com.example.thirdproject.favorite.entity.Favorite;
import com.example.thirdproject.post.entity.PostTemplate;
import com.example.thirdproject.profile.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 특정 유저가 좋아요를 눌렀는지 여부
    Optional<Favorite> findByProfileAndPostTemplate(Profile profile, PostTemplate postTemplate);

    // 공유한 템플릿 조회 시 내가 좋아요 눌렀는지 확인하기 위해
    // exist에 꼭 s 붙이자.. 저번에도 그런거 같은데 조심해야됨.
    boolean existsByProfileAndPostTemplate(Profile profile, PostTemplate postTemplate);

    // 내가 좋아요 한 템플릿 전체 조회
    @Query("SELECT f FROM Favorite f " +
    "JOIN FETCH f.postTemplate pt " +
    "JOIN FETCH pt.author " +
    "WHERE f.profile.id = :profileId " +
    "ORDER BY f.id DESC")
    Slice<Favorite> findSliceByProfileId(Long profileId, Pageable pageable);
}
