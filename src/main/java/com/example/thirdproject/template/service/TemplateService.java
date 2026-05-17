package com.example.thirdproject.template.service;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.template.dto.TemplateCreateRequest;
import com.example.thirdproject.template.dto.TemplateResponse;
import com.example.thirdproject.template.entity.Template;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TemplateService {
    TemplateResponse createTemplate(TemplateCreateRequest request, Profile owner);

    // 내가 다운받은 템플릿 목록 조회
    Slice<TemplateResponse> getMyDownloadedTemplates(Long userId, Pageable pageable);
}
