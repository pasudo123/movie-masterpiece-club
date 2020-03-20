package com.club.masterpiece.web.image.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.attachment.model.Attachment;

import java.util.List;

/**
 * Created by pasudo123 on 2019-12-25
 * Email: oraedoa@gmail.com
 **/
public interface ImageConverter {

    String convertImageTagToKeywordOnCreate(ArticleDto.CreateRequest dto);

    ArticleDto.UpdateRequest convertImageTagToKeywordOnUpdate(ArticleDto.UpdateRequest dto);

    ArticleDto.OneResponse convertKeywordToImageTag(ArticleDto.OneResponse dto, List<Attachment> attachmentList);
}
