package com.club.masterpiece.web.article.service;

import com.club.masterpiece.common.article.dto.ArticleDto;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleUpdateService {

    ArticleDto.OneResponse updateOneById(String articleId, ArticleDto.UpdateRequest dto);

}
