package com.example.thirdproject.favorite.service;

import com.example.thirdproject.favorite.entity.Favorite;
import com.example.thirdproject.favorite.repository.FavoriteRepository;
import com.example.thirdproject.post.entity.PostTemplate;
import com.example.thirdproject.post.repository.PostTemplateRepository;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService{
    private final FavoriteRepository favoriteRepository;
    private final PostTemplateRepository postTemplateRepository;
    private final ProfileRepository profileRepository;

    public boolean toggleFavorite(Long postTemplateId, Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        PostTemplate postTemplate = postTemplateRepository.findById(postTemplateId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾울 수 없습니다."));

        Optional<Favorite> existingFavorite = favoriteRepository.findByProfileAndPostTemplate(profile, postTemplate);

        if(existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
            postTemplate.subtractFavoriteCount();
            return false;
        } else {
            Favorite newFavorite = Favorite.of(profile, postTemplate);
            favoriteRepository.save(newFavorite);

            postTemplate.addFavoriteCount();
            return true;
        }
    }
}
