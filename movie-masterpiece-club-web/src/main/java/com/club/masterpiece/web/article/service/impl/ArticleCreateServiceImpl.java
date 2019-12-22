package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.repository.ArticleRepository;
import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.article.service.ArticleCommonService;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.image.service.ImageSaveService;
import com.club.masterpiece.web.util.ArticleIdGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier(value = "ImageLocalSaveServiceImpl") @NonNull
    private final ImageSaveService imageSaveService;
    private final ArticleCommonService articleCommonService;
    private final ArticleIdGenerator articleIdGenerator;
    private final ArticleRepository articleRepository;

    @UpdatableState
    @Override
    public ArticleDto.OneResponse create(final User user, final ArticleDto.CreateRequest dto) {

        log.debug("article title : {}", dto.getTitle());
        log.debug("article type : {}", dto.getType());

        final Article article = Article.builder()
                .articleId(articleIdGenerator
                        .generateId())
                .title(dto.getTitle())
                .content(articleCommonService
                        .convertImageTagToKeywordOnContent(dto))
                .type(dto.getType())
                .user(user)
                .build();

        final Article savedArticle = articleRepository.save(article);

        final List<Attachment> attachmentList = imageSaveService.save(savedArticle, dto.getContent());

        savedArticle.createAttachmentList(attachmentList);

        return new ArticleDto.OneResponse(savedArticle);
    }
}