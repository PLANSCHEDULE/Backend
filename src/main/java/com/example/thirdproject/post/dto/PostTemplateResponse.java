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
public class PostTemplateResponse {
    private Long postTemplateId;
    private String title;
    private String background;
    private String authorHandle;

    public static PostTemplateResponse from(PostTemplate postTemplate) {
        return PostTemplateResponse.builder()
                .postTemplateId(postTemplate.getId())
                .title(postTemplate.getTitle())
                .background(postTemplate.getBackground())
                .authorHandle(postTemplate.getAuthor().getHandle())
                .build();
    }
}
