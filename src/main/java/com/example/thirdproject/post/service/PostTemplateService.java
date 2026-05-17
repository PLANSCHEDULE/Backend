package com.example.thirdproject.post.service;


import com.example.thirdproject.post.dto.PostTemplateResponse;

public interface PostTemplateService {
    PostTemplateResponse shareToPost(Long templateId, Long userId);

    void downloadTemplate(Long postTemplateId, Long userId);
}
