package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.repository.ArticleRepository;
import com.club.masterpiece.web.article.service.ArticleFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleFindServiceImpl implements ArticleFindService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto.ListResponse findAll() {

        List<Article> articleList = articleRepository.findAll();

        return new ArticleDto.ListResponse(articleList);
    }
}
