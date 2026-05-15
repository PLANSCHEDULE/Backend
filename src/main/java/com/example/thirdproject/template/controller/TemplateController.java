package com.example.thirdproject.template.controller;

import com.example.thirdproject.global.commonResponse.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    // 템플릿 제작
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> TemplateCreate(HttpServletRequest request) {
        ApiResponse<Void> response = ApiResponse.created(
                "템플릿 제작 성공",
                null,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
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
