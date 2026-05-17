package com.example.thirdproject.post.service;

import com.example.thirdproject.post.dto.PostTemplateResponse;
import com.example.thirdproject.post.entity.PostTemplate;
import com.example.thirdproject.post.repository.PostTemplateRepository;
import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.repository.ProfileRepository;
import com.example.thirdproject.template.entity.Template;
import com.example.thirdproject.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostTemplateServiceImpl implements PostTemplateService{
    private final TemplateRepository templateRepository;
    private final PostTemplateRepository postTemplateRepository;
    private final ProfileRepository profileRepository;

    // ? override왜 까먹었지
    @Override
    @Transactional
    public PostTemplateResponse shareToPost(Long templateId, Long userId) {
        Profile author = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 프로필을 찾을 수 없습니다."));

        Template originalTemplate = templateRepository.findByIdWithItems(templateId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 템플릿입니다."));


        PostTemplate postTemplate = PostTemplate.createSnapshot(originalTemplate, author);

        return PostTemplateResponse.from(postTemplateRepository.save(postTemplate));


    }

    // 템플릿 다운로드
    @Override
    @Transactional
    public void downloadTemplate(Long postTemplateId, Long userId) {
        Profile downloader = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        PostTemplate postTemplate = postTemplateRepository.findByIdWithItems(postTemplateId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글에 1 증가
        postTemplate.increaseDownloadCount();;

        Template myNewTemplate = Template.createFromPost(postTemplate, downloader);
        templateRepository.save(myNewTemplate);

    }



}
