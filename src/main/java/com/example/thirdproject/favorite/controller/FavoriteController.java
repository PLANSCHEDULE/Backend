package com.example.thirdproject.favorite.controller;

import com.example.thirdproject.favorite.service.FavoriteService;
import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import com.example.thirdproject.post.dto.PostTemplateResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    // 찜 토클
    @PostMapping("/{postTemplateId}")
    public ResponseEntity<ApiResponse<Void>> toggleFavorite(
            @PathVariable Long postTemplateId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request) {
       boolean isAdded = favoriteService.toggleFavorite(postTemplateId, userDetails.getUser().getId());

       String message = isAdded ? "좋아요를 눌렀습니다." : "좋아요를 취소했습니다.";

       return ResponseEntity.status(HttpStatus.OK).body(
               ApiResponse.success(message, null, request.getRequestURI())
       );
    }


    // 찜한 템플릿 전체 조회
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Slice<PostTemplateResponse>>> getMyLikedPosts(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10)Pageable pageable,
            HttpServletRequest request
            ) {
        Slice<PostTemplateResponse> responses =
                favoriteService.getMyLikedTemplates(userDetails.getUser().getId(), pageable);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("내가 좋아요한 템플릿 전체 목록 조회 완료", responses, request.getRequestURI())
        );
    }
}
