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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String timestamp;
    private String path;

    private ApiResponse(int status, String message, T data, String path) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now().toString();
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



}
