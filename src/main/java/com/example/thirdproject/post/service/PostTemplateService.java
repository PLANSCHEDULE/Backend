package com.example.thirdproject.post.service;


import com.example.thirdproject.post.dto.PostTemplateResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostTemplateService {
    PostTemplateResponse shareToPost(Long templateId, Long userId);

    void downloadTemplate(Long postTemplateId, Long userId);

    Slice<PostTemplateResponse> getPostWithPaging(Long userId, Pageable pageable);

    // best top 10
    List<PostTemplateResponse> getTop10PopularTemplates(Long userId);

}
