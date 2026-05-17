package com.example.thirdproject.template.dto;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.template.entity.Template;
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

    public Template toEntity(Profile owner, String findBackground) {
        Template template = Template.builder()
                .title(this.title)
                .background(findBackground)
                .targetDate(this.targetDate)
                .owner(owner)
                .build();

        if(this.items != null) {
            this.items.stream()
                    .map(templateItemDto -> templateItemDto.toEntity(template))
                    .forEach(item -> template.getItems().add(item));
        }

        return template;
    }




}
