package com.club.masterpiece.web.article.service;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.attachment.model.Attachment;

import java.util.List;

/**
 * Created by pasudo123 on 2019-12-22
 * Email: oraedoa@gmail.com
 **/
public interface ArticleCommonService {

    String convertImageTagToKeywordOnContent(ArticleDto.CreateRequest dto);

    ArticleDto.OneResponse convertKeywordToImageTagOnContent(ArticleDto.OneResponse dto, List<Attachment> attachmentList);

}
