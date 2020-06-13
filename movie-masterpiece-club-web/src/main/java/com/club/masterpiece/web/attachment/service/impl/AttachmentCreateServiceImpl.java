package com.club.masterpiece.web.attachment.service.impl;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.dto.AttachmentDto;
import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.common.attachment.repository.AttachmentRepository;
import com.club.masterpiece.web.attachment.service.AttachmentCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AttachmentCreateServiceImpl implements AttachmentCreateService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public List<AttachmentDto.Response> createNewImage(Article article, final List<ImageDto.CreateResponse> imageResponses) {

        List<Attachment> attachments = imageResponses.stream()
                .map(Attachment::new)
                .collect(Collectors.toList());

        attachments.forEach(attachment -> attachment.setArticle(article));

        List<Attachment> savedAttachments = attachments.stream()
                .map(attachmentRepository::save)
                .collect(Collectors.toList());

        return savedAttachments.stream()
                .map(AttachmentDto.Response::new).collect(Collectors.toList());
    }
}
