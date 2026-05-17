package com.example.thirdproject.post.repository;

import com.example.thirdproject.post.entity.PostTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostTemplateRepository extends JpaRepository<PostTemplate, Long> {

    @Query("SELECT pt FROM PostTemplate pt JOIN FETCH pt.items WHERE pt.id = :id")
    Optional<PostTemplate> findByIdWithItems(Long id);

    // 웹은 pagable이 좋지만 앱에서는 사용자가 스크롤을 아래로 내릴 떄마다 데이터를 추가하는게 좋음
    // 모바일 뭏한스크롤에 부합한 거는 내가 요청한 개수보다 1개 더 많은 데이터를 조회하는 Slice가 더 적절함
    @Query("SELECT pt FROM PostTemplate pt JOIN FETCH pt.author")
    Slice<PostTemplate> findSliceWithAuthor(Pageable pageable);

    // 정해진 개수만큼 다운로드 순으로 리스트 조회
    // top 10으로 고정할 예정이라 slice 대신에 list를 사용함
    @Query("SELECT pt FROM PostTemplate pt JOIN FETCH pt.author ORDER BY pt.downloadCount DESC, pt.createdAt DESC")
    List<PostTemplate> findPopularTop(Pageable pageable);

}
