package com.example.thirdproject.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("일정 템플릿 공유 앱 Swagger")
                .description("일정 공유 앱 api 명세서입니다.");


        OpenAPI openApi = new OpenAPI()
                .info(info);

        return openApi;
    }
}
