package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.repository.ArticleRepository;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.util.ArticleIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @UpdatableState
    @Override
    public ArticleDto.OneResponse create(final User user, final ArticleDto.CreateRequest dto) {

        log.debug("article title : {}", dto.getTitle());
        log.debug("article content : {}", dto.getContent());
        log.debug("article type : {}", dto.getType());

        Article article = Article.builder()
                .articleId(articleIdGenerator.generateId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .type(dto.getType())
                .user(user)
                .build();

        Article savedArticle = articleRepository.save(article);

        return new ArticleDto.OneResponse(savedArticle);
    }
}
