package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.repository.ArticleRepository;
import com.club.masterpiece.web.article.service.ArticleCreateService;
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

    private ArticleRepository articleRepository;

    @Override
    public ArticleDto.OneResponse create(ArticleDto.createRequest dto) {

        log.debug("article content : {}", dto.getArticleContent());
        log.debug("article type : {}", dto.getArticleType());

        return null;
    }
}
