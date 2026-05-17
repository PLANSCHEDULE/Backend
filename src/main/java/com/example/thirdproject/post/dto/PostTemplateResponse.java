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

    private int favoriteCount;
    private int downloadCount;
    private boolean isFavorite;

    // 공유하기 api 용
    public static PostTemplateResponse from(PostTemplate postTemplate) {
        return PostTemplateResponse.builder()
                .postTemplateId(postTemplate.getId())
                .title(postTemplate.getTitle())
                .background(postTemplate.getBackground())
                .authorHandle(postTemplate.getAuthor().getHandle())
                .favoriteCount(0)
                .downloadCount(0)
                .isFavorite(false)
                .build();
    }

    // 공유된 template 전체 조회용
    public static PostTemplateResponse AllPostTemplate(PostTemplate postTemplate, boolean isFavorite) {
        return PostTemplateResponse.builder()
                .postTemplateId(postTemplate.getId())
                .title(postTemplate.getTitle())
                .background(postTemplate.getBackground())
                .authorHandle(postTemplate.getAuthor().getHandle())
                .favoriteCount(postTemplate.getFavoriteCount())
                .downloadCount(postTemplate.getDownloadCount())
                .isFavorite(isFavorite)
                .build();
    }
}
