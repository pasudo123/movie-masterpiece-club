package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.repository.ArticleRepository;
import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.attachment.service.AttachmentCreateService;
import com.club.masterpiece.web.infra.service.WebClientService;
import com.club.masterpiece.web.pojo.CustomImageData;
import com.club.masterpiece.web.util.ArticleIdGenerator;
import com.club.masterpiece.web.util.image.ImageDataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleCreateServiceImpl implements ArticleCreateService {

    private final ArticleIdGenerator articleIdGenerator;
    private final ArticleRepository articleRepository;
    private final WebClientService webClientService;
    private final ImageDataUtil imageDataUtil;

    private final AttachmentCreateService attachmentCreateService;

    @Override
    public ArticleDto.OneResponse create(final User user, final ArticleDto.CreateRequest dto) {

        final List<CustomImageData> imageDataList = imageDataUtil.extractImage(dto.getContent());
        final List<ImageDto.CreateResponse> imageResponses = webClientService.uploadImages(imageDataList);

        final Article article = Article.builder()
            .id(articleIdGenerator.generateId())
            .title(dto.getTitle())
            .type(dto.getType())
            .user(user)
            .build();

        final Article savedArticle = articleRepository.save(article);

        attachmentCreateService.createNewImage(savedArticle, imageResponses);
        savedArticle.setContent(imageDataUtil.changeImageSrcIfPossible(dto.getContent(), savedArticle.getAttachmentList()));

        return new ArticleDto.OneResponse(savedArticle);
    }

    @Override
    public String extractImageBytes(ArticleDto.CreateRequest dto) {
        final String content = dto.getContent();

        return null;
    }
}