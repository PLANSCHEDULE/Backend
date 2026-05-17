package com.example.thirdproject.template.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.service.ProfileService;
import com.example.thirdproject.template.dto.TemplateCreateRequest;
import com.example.thirdproject.template.dto.TemplateResponse;
import com.example.thirdproject.template.service.TemplateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final ProfileService profileService;

    // 템플릿 제작
    @PostMapping
    public ResponseEntity<ApiResponse<TemplateResponse>> TemplateCreate(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody TemplateCreateRequest templateCreateRequest,
            HttpServletRequest request) {

        // jwt로 profile 엔티티 조회
        Profile profile = profileService.findProfileByUserId(userDetails.getUser().getId());


        TemplateResponse response = templateService.createTemplate(templateCreateRequest, profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created("템플릿 생성 완료", response, request.getRequestURI())
        );

    }

    // 템플릿 수정
    @PatchMapping("/{templateId}")
    public ResponseEntity<ApiResponse<Void>> updateTemplate(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success("특정 템플릿 조회", null, request.getRequestURI()));
    }


    // 특정 템플릿 조회
    @GetMapping("/{templateId}")
    public ResponseEntity<ApiResponse<Void>> getTemplate(
            HttpServletRequest request
    ) {

        return ResponseEntity.ok(ApiResponse.success("특정 템플릿 조회", null, request.getRequestURI()));

    }

    // 템플릿 삭제
    @DeleteMapping("/{templateId}")
    public ResponseEntity<ApiResponse<Void>> DeleteTemplate(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success("", null, request.getRequestURI()));
    }

    // 템플릿 검색 조회 (pageable 필요)
    @GetMapping
    public ResponseEntity<ApiResponse<Void>> searchTemplates(
            @PageableDefault(page = 0, size = 6) Pageable pageable,
            HttpServletRequest request
    ) {
        return null;
    }

    // 템플릿 전체 조회 (pageable 필요)

    // 인기 템플릿 조회 (한 6개 정도?)

}
