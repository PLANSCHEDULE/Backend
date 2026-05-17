package com.example.thirdproject.template.service;

import com.example.thirdproject.profile.entity.Profile;
import com.example.thirdproject.profile.repository.ProfileRepository;
import com.example.thirdproject.template.dto.TemplateCreateRequest;
import com.example.thirdproject.template.dto.TemplateResponse;
import com.example.thirdproject.template.entity.Template;
import com.example.thirdproject.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemplateServiceImpl implements TemplateService{
    private final TemplateRepository templateRepository;
    private final ProfileRepository profileRepository;

    private static final List<String> PALLETTE = List.of(
            "#FFB380", "#B39DDB", "#A5D6A7", "#90CAF9", "#FFF59D", "#F48FB1", "#80CBC4"
    );

    // 템플릿 생성
    @Override
    @Transactional
    public TemplateResponse createTemplate(TemplateCreateRequest request, Profile owner) {

        // 배경 결정
        // 사진이 있다면 그대로 사용, 없으면 랜덤 색상 배치
        String setBackground = (request.getBackground() != null && !request.getBackground().isBlank())
                // 만약 여기가 사진 URL이면 사진을 가져올거고 아니라면 랜덤 배경 가져오기
                ? request.getBackground()
                : getRandomColor();

        // Template 엔티티 생성
        Template template = request.toEntity(owner, setBackground);

        Template saveTemplate = templateRepository.save(template);

        return TemplateResponse.from(saveTemplate);

    }


    private String getRandomColor() {
        return PALLETTE.get(new Random().nextInt(PALLETTE.size()));
    }

    // 내가 다운로드 받은 템플릿 조회
    @Override
    @Transactional(readOnly = true)
    public Slice<TemplateResponse> getMyDownloadedTemplates(Long userId, Pageable pageable) {
        Profile currentProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Slice<Template> downloadedSlice = templateRepository.findDownloadedTemplatesByOwnerId(currentProfile.getId(), pageable);

        return downloadedSlice.map(TemplateResponse::from);
    }

}
