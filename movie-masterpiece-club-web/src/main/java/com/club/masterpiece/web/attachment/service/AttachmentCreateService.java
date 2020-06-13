package com.club.masterpiece.web.attachment.service;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.dto.AttachmentDto;
import com.club.masterpiece.common.attachment.dto.ImageDto;

import java.util.List;

public interface AttachmentCreateService {

    List<AttachmentDto.Response> createNewImage(Article article, List<ImageDto.CreateResponse> imageResponses);
}
