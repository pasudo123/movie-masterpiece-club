//package com.club.masterpiece.web.image.service.impl;
//
//import com.club.masterpiece.common.article.dto.ArticleDto;
//import com.club.masterpiece.common.attachment.model.Attachment;
//import com.club.masterpiece.web.exception.BusinessException;
//import com.club.masterpiece.web.image.service.ImageConverter;
//import com.club.masterpiece.web.util.image.ImageDataPostProcessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
///**
// * Created by pasudo123 on 2019-12-30
// * Email: oraedoa@gmail.com
// **/
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ImagePrivateServerConverterImpl implements ImageConverter {
//
//    private final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");
//    private final ImageDataPostProcessor imageDataPostProcessor;
//    private final ObjectMapper mapper;
//
//    @Value("${image.server-path}")
//    private String serverPath;
//
//    @Value("${image.root-path}")
//    private String rootPath;
//
//    @Value("${image.resource-path}")
//    private String resourcePath;
//
//    @Value("${keyword.image}")
//    private String imageKeyword;
//
//    @Override
//    public String convertImageTagToKeywordOnCreate(ArticleDto.CreateRequest dto) {
//
//        return dto.getContent()
//                .replaceAll(String.valueOf(IMAGE_TAG_PATTERN), imageKeyword);
//    }
//
//    @Override
//    public ArticleDto.UpdateRequest convertImageTagToKeywordOnUpdate(ArticleDto.UpdateRequest dto) {
//
//        return ArticleDto.UpdateRequest
//                .builder()
//                .title(dto.getTitle())
//                .content(dto.getContent().replaceAll(String.valueOf(IMAGE_TAG_PATTERN), imageKeyword))
//                .build();
//    }
//
//    @Override
//    public ArticleDto.OneResponse convertKeywordToImageTag(ArticleDto.OneResponse dto, List<Attachment> attachmentList) {
//
//        final List<String> convertContentList = new ArrayList<>();
//
//        for (Attachment attachment : attachmentList) {
//
//            // 첨부파일 삭제여부
//            if(attachment.isDelete()) {
//                continue;
//            }
//
//            try {
//
//                final String url = attachment.getUrl().replaceAll(resourcePath, resourcePath);
//                final Map<String, String> properties = mapper.readValue(attachment.getProperties(), Map.class);
//
//                convertContentList.add(imageDataPostProcessor.createStaticImage(url, properties));
//
//            } catch (Exception e) {
//                throw new BusinessException("JSON 매핑 시 에러가 발생하였습니다. : " + e.getMessage());
//            }
//        }
//
//        if(convertContentList.size() == 0) {
//            throw new BusinessException("이미지가 있음에도 불구하고 이미지를 가져오지 못하였습니다.");
//        }
//
//        dto.changeImageIncludeContent(
//                imageDataPostProcessor.createImageIncludeContent(dto, convertContentList));
//
//        return dto;
//    }
//}
