package com.example.thirdproject.template.entity;

import com.example.thirdproject.global.entity.BaseTime;
import com.example.thirdproject.post.entity.PostTemplate;
import com.example.thirdproject.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "templates")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Template extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // #으로 시작하면 색상, 아니면 이미지 url
    private String background;

    private LocalDate targetDate;

    // 원작자 handle
    private String originalAuthorHandle;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile owner;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<TemplateItem> items = new ArrayList<>();


    @Builder
    public Template(String title, String background, LocalDate targetDate,
                    String originalAuthorHandle, Profile owner) {
        this.title = title;
        this.background = background;
        this.targetDate = targetDate;
        this.originalAuthorHandle = originalAuthorHandle;
        this.owner = owner;
    }

    public static Template createFromPost(PostTemplate postTemplate, Profile owner) {
        Template newTemplate = Template.builder()
                .title(postTemplate.getTitle())
                .background(postTemplate.getBackground())
                .originalAuthorHandle(postTemplate.getAuthor().getHandle())
                .owner(owner)
                .build();

        List<TemplateItem> myItems = postTemplate.getItems().stream()
                .map(postItem -> TemplateItem.fromPostItem(postItem, newTemplate))
                .toList();

        newTemplate.getItems().addAll(myItems);
        return newTemplate;
    }

    // 업데이트
    public void updateTemplate(String title, LocalDate targetDate) {
        if(title != null & !title.isBlank()) {
            this.title = title;
        }
        if(targetDate != null) {
            this.targetDate = targetDate;
        }
    }

}
