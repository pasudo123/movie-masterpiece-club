package com.club.masterpiece.web.article.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.web.global.pojo.PageRequestDto;
import org.springframework.data.domain.Page;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleFindService {

    ArticleDto.ListResponse findAll();

    Long getCount();

    Page<ArticleDto.OneResponse> findPartialByPage(PageRequestDto dto);

    ArticleDto.OneResponse findOneById(String articleId);

    Article findEntityById(String articleId);
}
