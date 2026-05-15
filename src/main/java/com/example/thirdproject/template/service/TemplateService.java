package com.example.thirdproject.template.service;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.template.dto.TemplateCreateRequest;

public interface TemplateService {
    Long createTemplate(TemplateCreateRequest request, Profile owner);
}
