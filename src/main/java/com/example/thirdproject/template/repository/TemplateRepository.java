package com.example.thirdproject.template.repository;

import com.example.thirdproject.template.entity.Template;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TemplateRepository extends JpaRepository<Template, Long> {

    // N + 1 문제 방지 (템플릿 가져올 때)
    @Query("SELECT t FROM Template t JOIN FETCH t.items WHERE t.id = :id")
    Optional<Template> findByIdWithItems(Long id);
}
