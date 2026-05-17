package com.example.thirdproject.template.repository;

import com.example.thirdproject.template.entity.Template;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TemplateRepository extends JpaRepository<Template, Long> {

    // N + 1 문제 방지 (템플릿 가져올 때)
    @Query("SELECT t FROM Template t JOIN FETCH t.items WHERE t.id = :id")
    Optional<Template> findByIdWithItems(Long id);

    // 사용자의 다운로드 받은 일정만 조회
    @Query("SELECT t FROM Template t WHERE t.owner.id = :ownerId AND t.originalAuthorHandle IS NOT NULL ORDER BY t.createdAt DESC")
    Slice<Template> findDownloadedTemplatesByOwnerId(Long ownerId, Pageable pageable);

    // 내 템플릿 전체 조회
    @Query("SELECT t FROM Template t WHERE t.owner.id = :profileId ORDER BY t.targetDate DESC, t.createdAt DESC")
    Slice<Template> findSliceByOwnerId(Long profileId, Pageable pageable);

}
