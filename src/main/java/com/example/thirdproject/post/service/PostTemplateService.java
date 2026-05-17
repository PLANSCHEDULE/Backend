package com.example.thirdproject.post.service;


import com.example.thirdproject.post.dto.PostTemplateResponse;
import com.example.thirdproject.profile.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostTemplateService {
    PostTemplateResponse shareToPost(Long templateId, Long userId);

    void downloadTemplate(Long postTemplateId, Long userId);

    Slice<PostTemplateResponse> getPostWithPaging(Long userId, Pageable pageable);
}
