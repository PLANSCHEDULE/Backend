package com.example.thirdproject.template.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.service.ProfileService;
import com.example.thirdproject.template.dto.TemplateCreateRequest;
import com.example.thirdproject.template.dto.TemplateResponse;
import com.example.thirdproject.template.dto.TemplateUpdateRequest;
import com.example.thirdproject.template.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Template controller", description = "템플릿 controller")
@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final ProfileService profileService;

    // 템플릿 제작
    @Operation(summary = "템플릿 생성 api")
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

//    // 템플릿 수정
//    @PatchMapping("/{templateId}")
//    public ResponseEntity<ApiResponse<Void>> updateTemplate(
//            HttpServletRequest request
//    ) {
//        return ResponseEntity.ok(ApiResponse.success("특정 템플릿 조회", null, request.getRequestURI()));
//    }


    // 특정 템플릿 조회
//    @GetMapping("/{templateId}")
//    public ResponseEntity<ApiResponse<Void>> getTemplate(
//            HttpServletRequest request
//    ) {
//
//        return ResponseEntity.ok(ApiResponse.success("특정 템플릿 조회", null, request.getRequestURI()));
//
//    }

    // 템플릿 삭제
//    @DeleteMapping("/{templateId}")
//    public ResponseEntity<ApiResponse<Void>> DeleteTemplate(
//            HttpServletRequest request
//    ) {
//        return ResponseEntity.ok(ApiResponse.success("", null, request.getRequestURI()));
//    }

    // 템플릿 검색 조회 (pageable 필요)
//    @GetMapping
//    public ResponseEntity<ApiResponse<Void>> searchTemplates(
//            @PageableDefault(page = 0, size = 6) Pageable pageable,
//            HttpServletRequest request
//    ) {
//        return null;
//    }

    // 템플릿 전체 조회 (pageable 필요)
    @Operation(summary = "템플릿 전체 조회 api")
    @GetMapping
    public ResponseEntity<ApiResponse<Slice<TemplateResponse>>> getMyAllTemplates(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10)Pageable pageable,
            HttpServletRequest request
            ) {
        Slice<TemplateResponse> response =
                templateService.getMyAllTemplates(userDetails.getUser().getId(), pageable);

        return ResponseEntity.ok(
                ApiResponse.success("내 템플릿 전체 조회 완료", response, request.getRequestURI())
        );
    }

    // 내가 다운 받은 템플릿 목록 조회
    @Operation(summary = "사용자가 다운 받은 템플릿 조회 api")
    @GetMapping("/me/downloaded")
    public ResponseEntity<ApiResponse<Slice<TemplateResponse>>> getMyDownloadedTemplates(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10)org.springframework.data.domain.Pageable pageable,
            HttpServletRequest request
            ) {
        Slice<TemplateResponse> response =
                templateService.getMyDownloadedTemplates(userDetails.getUser().getId(), pageable);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("다운로드 받은 템플릿 목록 조회 완료", response, request.getRequestURI())
        );
    }

    // 템플릿 업데이트 관련 api
    // 통째로 뒤집어쓰는거라 PUT
    @Operation(summary = "템플릿 수정 api")
    @PutMapping("/{templateId}")
    public ResponseEntity<ApiResponse<TemplateResponse>> updateTemplate(
            @PathVariable Long templateId,
            @RequestBody TemplateUpdateRequest updateRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request
            ) {
        TemplateResponse response =
                templateService.updateTemplate(templateId, userDetails.getUser().getId(), updateRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("일정이 수정되었습니다.", response, request.getRequestURI())
                );
    }

}
