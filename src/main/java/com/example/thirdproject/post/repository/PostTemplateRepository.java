package com.example.thirdproject.post.repository;

import com.example.thirdproject.post.entity.PostTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface PostTemplateRepository extends JpaRepository<PostTemplate, Long> {

    @Query("SELECT pt FROM PostTemplate pt JOIN FETCH pt.items WHERE pt.id = :id")
    Optional<PostTemplate> findByIdWithItems(Long id);



}
