package com.example.thirdproject.template.dto;

import com.example.thirdproject.template.entity.TemplateItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class TemplateItemResponse {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime itemTime;

    private String content;
    private int sequence;
    private boolean isAlarmOn;
    private boolean isCompleted;

    public static TemplateItemResponse from(TemplateItem item) {
        return TemplateItemResponse.builder()
                .id(item.getId())
                .itemTime(item.getItemTime())
                .content(item.getContent())
                .sequence(item.getSequence())
                .isAlarmOn(item.isAlarmOn())
                .isCompleted(item.isCompleted())
                .build();
    }
}
