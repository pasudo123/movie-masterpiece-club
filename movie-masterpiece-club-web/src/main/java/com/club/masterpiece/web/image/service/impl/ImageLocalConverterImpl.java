package com.club.masterpiece.web.image.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.web.exception.BusinessException;
import com.club.masterpiece.web.image.service.ImageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by pasudo123 on 2019-12-25
 * Email: oraedoa@gmail.com
 **/
@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"dev", "staging"})
public class ImageLocalConverterImpl implements ImageConverter {

    private final ObjectMapper mapper;
    private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");

    @Value("${keyword.image}")
    private String imageKeyword;

    @Override
    public String convertImageTagToKeywordOnCreate(final ArticleDto.CreateRequest dto) {

        return dto.getContent()
                .replaceAll(String.valueOf(IMAGE_TAG_PATTERN), imageKeyword);
    }

    @Override
    public ArticleDto.UpdateRequest convertImageTagToKeywordOnUpdate(ArticleDto.UpdateRequest dto) {

        return ArticleDto.UpdateRequest
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent().replaceAll(String.valueOf(IMAGE_TAG_PATTERN), imageKeyword))
                .build();
    }

    @Override
    public ArticleDto.OneResponse convertKeywordToImageTag(final ArticleDto.OneResponse dto, final List<Attachment> attachmentList) {

        final List<String> convertContentList = new ArrayList<>();

        for (Attachment attachment : attachmentList) {

            // 삭제처리 첨부파일 : continue;
            if(attachment.isDelete()){
                continue;
            }

            try {

                final String url = attachment.getUrl();
                final byte[] fileContent = FileUtils.readFileToByteArray(new File(url));
                final String encodeString = Base64.getEncoder().encodeToString(fileContent);
                final Map<String, String> properties = mapper.readValue(attachment.getProperties(), Map.class);

                convertContentList.add(createImageTag(encodeString, properties));

            } catch (IOException e) {
                throw new BusinessException("파일경로를 바이트 코드로 변환하는 도중에 에러가 발생하였습니다.");
            }
        }

        final String[] partialContents = dto.getContent().split(imageKeyword);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < partialContents.length; i++) {

            sb.append(partialContents[i]);

            if((partialContents.length-1) == i) {
                continue;
            }

            sb.append(convertContentList.get(i));
        }

        dto.changeImageIncludeContent(sb.toString());

        return dto;
    }

    private String createImageTag(final String encodeString, final Map<String, String> properties) {

        StringJoiner joiner = new StringJoiner(" ", "<", ">");

        joiner.add("img");
        joiner.add("src=\"data:image/png;base64," + encodeString + "\"");

        for(String key : properties.keySet()) {
            joiner.add(key + "=\"" + properties.get(key) + "\"");
        }

        return joiner.toString();
    }
}
