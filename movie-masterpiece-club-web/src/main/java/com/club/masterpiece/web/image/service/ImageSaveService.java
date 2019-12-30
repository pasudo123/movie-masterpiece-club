package com.club.masterpiece.web.image.service;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.model.Attachment;

import java.util.List;

/**
 * Created by pasudo123 on 2019-12-11
 * Email: oraedoa@gmail.com
 **/
public interface ImageSaveService {

    static final String HYPHEN = "-";
    static final String SPACE = "";
    static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    static final String PNG = ".png";

    public List<Attachment> save(Article article, String content);
}
