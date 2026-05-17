package com.example.thirdproject.favorite.service;

import com.example.thirdproject.post.dto.PostTemplateResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FavoriteService {
    boolean toggleFavorite(Long postTemplateId, Long userId);

    Slice<PostTemplateResponse> getMyLikedTemplates(Long userId, Pageable pageable);
}
