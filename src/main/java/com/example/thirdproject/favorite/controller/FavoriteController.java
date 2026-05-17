package com.example.thirdproject.favorite.controller;

import com.example.thirdproject.favorite.service.FavoriteService;
import com.example.thirdproject.global.commonResponse.ApiResponse;
import com.example.thirdproject.global.security.jwt.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
}
