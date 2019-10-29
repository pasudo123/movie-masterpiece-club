package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.article.service.ArticleUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleUpdateServiceImpl implements ArticleUpdateService {

    private final ArticleFindService articleFindService;

    @UpdatableState
    @Override
    public ArticleDto.OneResponse updateOneById(String articleId, ArticleDto.UpdateRequest dto) {

        Article foundArticle = articleFindService.findEntityById(articleId);
        foundArticle.updateArticle(dto);

        return new ArticleDto.OneResponse(foundArticle);
    }
}
