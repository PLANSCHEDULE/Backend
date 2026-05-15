package com.example.thirdproject.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateItemDto {
    private LocalTime time;
    private String content;
    private int sequence;
    private boolean isAlarmOn;
}
