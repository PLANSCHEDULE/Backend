package com.example.thirdproject.template.dto;

import com.example.thirdproject.post.entity.PostTemplateItem;
import com.example.thirdproject.template.entity.Template;
import com.example.thirdproject.template.entity.TemplateItem;
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

    public TemplateItem toEntity(Template template) {
        return TemplateItem.builder()
                .itemTime(this.time)
                .content(this.content)
                .isCompleted(false)
                .sequence(this.sequence)
                .isAlarmOn(this.isAlarmOn)
                .template(template)
                .build();
    }
}
