package com.example.thirdproject.post.dto;

import com.example.thirdproject.post.entity.PostTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostTemplateResponseDto {
    private Long postTemplateId;
    private String title;
    private String background;
    private String authorHandle;

    public static PostTemplateResponseDto from(PostTemplate postTemplate) {
        return PostTemplateResponseDto.builder()
                .postTemplateId(postTemplate.getId())
                .title(postTemplate.getTitle())
                .background(postTemplate.getBackground())
                .authorHandle(postTemplate.getAuthor().getHandle())
                .build();
    }
}
