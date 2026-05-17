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
public class TemplateUpdateRequest {
    private String title;
    private LocalDate targetDate;
    private List<TemplateUpdateItem> items;

}
