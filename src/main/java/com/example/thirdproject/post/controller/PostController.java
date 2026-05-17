package com.example.thirdproject.post.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.post.dto.PostTemplateResponse;
import com.example.thirdproject.post.service.PostTemplateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostTemplateService postTemplateService;

    // 템플릿 공유
    @PostMapping("/share/{templateId}")
    public ResponseEntity<ApiResponse<PostTemplateResponse>> shareTemplate(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long templateId,
            HttpServletRequest request
            ) {
        PostTemplateResponse response = postTemplateService.shareToPost(templateId, userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created("템플릿 공유 완료", response, request.getRequestURI())
        );


    }
    // 다운로드 수 증가 로직


    // 가장 많이 다운로드 된 인기 템플릿
}
