package com.example.thirdproject.global.commonResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    // format 변경일 때 데이터 타입 LocalDateTime 가능
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;
    private String path;

    private ApiResponse(int status, String message, T data, String path) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    // 데이터가 없는 성공 응답
    public static <T> ApiResponse<T> success(String message, String path) {
        return new ApiResponse<>(HttpStatus.OK.value(), message, null, path);
    }

    // 성공 응답
    public static <T> ApiResponse<T> success(String message, T data, String path) {
        return new ApiResponse<>(HttpStatus.OK.value(), message, data, path);
    }

    // 201 created 응답
    public static <T> ApiResponse<T> created(String message, T data, String path) {
        return new ApiResponse<>(HttpStatus.CREATED.value(),message, data, path);
    }

}
