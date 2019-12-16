package com.club.masterpiece.web.image.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.user.model.User;

/**
 * Created by pasudo123 on 2019-12-11
 * Email: oraedoa@gmail.com
 **/
public interface ImageSaveService {

    public Object saveImage(User user, ArticleDto.CreateRequest dto);
}
