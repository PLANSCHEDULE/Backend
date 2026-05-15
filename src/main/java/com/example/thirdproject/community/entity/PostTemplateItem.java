package com.example.thirdproject.community.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "post_template_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTemplateItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime itemTime;

    @Column(nullable = false)
    private String content;

    private int sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_template_id")
    private PostTemplate postTemplate;

    @Builder
    public PostTemplateItem(LocalTime itemTime, String content, int sequence, PostTemplate postTemplate) {
        this.itemTime = itemTime;
        this.content = content;
        this.sequence = sequence;
        this.postTemplate = postTemplate;
    }

}
