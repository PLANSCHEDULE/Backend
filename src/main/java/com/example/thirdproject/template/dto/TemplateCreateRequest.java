package com.example.thirdproject.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateCreateRequest {
    private String title;
    private String background;
    private LocalDate targetDate;
    private List<TemplateItemDto> items;

}
