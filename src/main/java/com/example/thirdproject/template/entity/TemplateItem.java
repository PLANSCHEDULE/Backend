package com.example.thirdproject.template.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "template_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime time;

    @Column(nullable = false)
    private String content;

    // 체크박스 상태
    private boolean isCompleted = false;

    // 아이템 순서를 체크하기 위해
    private int sequence;

    // 나중에 알람 기능을 추가할 지도 모르기 때문에 넣어둔 컬럼
    private boolean isAlarmOn = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;


    @Builder
    public TemplateItem(LocalTime time, String content, boolean isCompleted, int sequence, boolean isAlarmOn, Template template) {
        this.time = time;
        this.content = content;
        this.isCompleted = isCompleted;
        this.sequence = sequence;
        this.isAlarmOn = isAlarmOn;
        this.template = template;
    }

}
