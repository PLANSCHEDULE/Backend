package com.example.thirdproject.favorite.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    // 찜 추가
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> FavoriteCreate(HttpServletRequest request) {
        ApiResponse<Void> response = ApiResponse.created(
                "",
                null,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


    // 찜 삭제
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<ApiResponse<Void>> updateTemplate(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success("", null, request.getRequestURI()));
    }


    // 찜한 템플릿 전체 조회
}
