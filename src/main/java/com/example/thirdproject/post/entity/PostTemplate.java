package com.example.thirdproject.post.entity;

import com.example.thirdproject.global.entity.BaseTime;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.template.entity.Template;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post_templates")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTemplate extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String background;

    // 잠깐 근데 생각해보니 여기서도 통계 낼건데 개인 템플릿은
    // 통계 자료 필요없지 않으려나? 아 복사 개념이다보니까 db 어려워진다..
    private int favoriteCount = 0;
    private int downloadCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile author;

    @OneToMany(mappedBy = "postTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTemplateItem> items = new ArrayList<>();

    @Builder
    public PostTemplate(String title, String background, Profile author) {
        this.title = title;
        this.background = background;
        this.author = author;
    }

    // 오류 _> static이 아니여서 못찾고 있었음. 그리고 backgorund에 getbackground로 수정
    public static PostTemplate createSnapshot(Template template, Profile author) {
        PostTemplate postTemplate = PostTemplate.builder()
                .title(template.getTitle())
                .background(template.getBackground())
                .author(author)
                .build();

        List<PostTemplateItem> postItems = template.getItems().stream()
                .map(item -> PostTemplateItem.from(item, postTemplate))
                .toList();

        postTemplate.getItems().addAll(postItems);
        return postTemplate;
    }
}
