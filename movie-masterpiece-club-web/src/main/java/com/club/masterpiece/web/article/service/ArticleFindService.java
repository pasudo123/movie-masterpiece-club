package com.club.masterpiece.web.article.service;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.model.Article;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleFindService {

    ArticleDto.ListResponse findAll();

    ArticleDto.OneResponse findOneById(String articleId);

    Article findEntityById(String articleId);

}
