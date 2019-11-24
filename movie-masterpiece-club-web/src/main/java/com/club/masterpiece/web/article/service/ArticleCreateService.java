package com.club.masterpiece.web.article.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.user.model.User;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleCreateService {

    ArticleDto.OneResponse create(User user, ArticleDto.CreateRequest dto);

}
