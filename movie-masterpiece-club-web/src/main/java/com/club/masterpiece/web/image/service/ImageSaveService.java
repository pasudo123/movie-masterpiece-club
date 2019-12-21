package com.club.masterpiece.web.image.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.common.user.model.User;

import java.util.List;

/**
 * Created by pasudo123 on 2019-12-11
 * Email: oraedoa@gmail.com
 **/
public interface ImageSaveService {

    public List<Attachment> save(ArticleDto.CreateRequest dto);
}
