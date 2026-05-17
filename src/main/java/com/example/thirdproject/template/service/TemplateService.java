package com.example.thirdproject.template.service;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.template.dto.TemplateCreateRequest;
import com.example.thirdproject.template.dto.TemplateResponse;

public interface TemplateService {
    TemplateResponse createTemplate(TemplateCreateRequest request, Profile owner);
}
