package com.example.thirdproject.post.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.post.dto.PostTemplateResponse;
import com.example.thirdproject.post.service.PostTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post controller", description = "공유된 템플릿 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostTemplateService postTemplateService;

    // 템플릿 공유
    @Operation(summary = "템플릿 공유")
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
    @Operation(summary = "템플릿 다운로드 수 조회")
    @PostMapping("/{postTemplateId}/download")
    public ResponseEntity<ApiResponse<Void>> downloadTemplate(
            @PathVariable Long postTemplateId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request
    ) {
        postTemplateService.downloadTemplate(postTemplateId, userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("템플릿 다운로드 완료", null, request.getRequestURI())
        );
    }

    // 전체 공유된 템플릿 조회
    @Operation(summary = "공유된 템플릿 전체 조회")
    @GetMapping("/paging")
    public ResponseEntity<ApiResponse<Slice<PostTemplateResponse>>> getAllTemplates(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable,
            HttpServletRequest request
            ) {
        Slice<PostTemplateResponse> responses =
                postTemplateService.getPostWithPaging(userDetails.getUser().getId(), pageable);

        return  ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("공유된 템플릿 전체 조회 완료", responses, request.getRequestURI())
        );
    }


    // 가장 많이 다운로드 된 인기 템플릿 (Best 10개만)
    @Operation(summary = "다운로드 많은 템플릿 Best 10")
    @GetMapping("/top10")
    public ResponseEntity<ApiResponse<List<PostTemplateResponse>>> getTop10(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request
    ) {
        List<PostTemplateResponse> response =
                postTemplateService.getTop10PopularTemplates(userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("인기 템플릿 TOP 10 조회 완료", response, request.getRequestURI())
        );
    }

    // 템플릿 검색 api 필요
}
