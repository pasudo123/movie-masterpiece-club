package com.club.masterpiece.web.image.service;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.model.Attachment;

import java.util.List;

/**
 * Created by pasudo123 on 2019-12-11
 * Email: oraedoa@gmail.com
 **/
public interface ImageSaveService {
    public List<Attachment> save(Article article, String content);
}
