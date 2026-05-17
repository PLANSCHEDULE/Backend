package com.example.thirdproject.template.dto;

import com.example.thirdproject.template.entity.Template;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TemplateResponse {
    private Long id;
    private String title;
    private String background;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate targetDate;

    private List<TemplateItemResponse> items;

    public static TemplateResponse from(Template template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .title(template.getTitle())
                .background(template.getBackground())
                .targetDate(template.getTargetDate())
                .items(template.getItems().stream()
                        .map(TemplateItemResponse::from)
                        .toList())
                .build();
    }

}
