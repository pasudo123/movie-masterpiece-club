package com.club.masterpiece.web.article.service;

import com.club.masterpiece.web.article.dto.ArticleDto;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleCreateService {

    ArticleDto.OneResponse create(ArticleDto.createRequest dto);

}
