package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.repository.ArticleRepository;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.user.model.User;
import com.club.masterpiece.web.user.service.UserFindService;
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

    private final UserFindService userFindService;
    private final ArticleIdGenerator articleIdGenerator;
    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto.OneResponse create(final ArticleDto.createRequest dto) {

        log.debug("article content : {}", dto.getContent());
        log.debug("article type : {}", dto.getType());

        // temp
        User foundUser = userFindService.findOneById("2739a8d6-97f5-48af-a198-da8392d271e7");

        Article article = Article.builder().
                articleId(articleIdGenerator.generateId())
                .content(dto.getContent())
                .type(dto.getType())
                .user(foundUser)
                .build();

        Article savedArticle = articleRepository.save(article);

        return new ArticleDto.OneResponse(savedArticle);
    }
}
