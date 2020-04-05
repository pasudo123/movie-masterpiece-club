//package com.club.masterpiece.web.image.service.impl;
//
//import com.club.masterpiece.common.article.model.Article;
//import com.club.masterpiece.common.attachment.dto.ImageDto;
//import com.club.masterpiece.common.attachment.dto.ImageExtractElement;
//import com.club.masterpiece.common.attachment.model.Attachment;
//import com.club.masterpiece.common.attachment.repository.AttachmentRepository;
//import com.club.masterpiece.web.image.service.ImageSaveService;
//import com.club.masterpiece.web.image.service.ImageUploadService;
//import com.club.masterpiece.web.util.image.ImageDataPreProcessor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by pasudo123 on 2019-12-14
// * Email: oraedoa@gmail.com
// **/
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ImageSaveServiceImpl implements ImageSaveService {
//
//    @Value("${image.resource-path}")
//    private String resourcePath;
//
//    private final ImageDataPreProcessor imageDataPreProcessor;
//    private final AttachmentRepository attachmentRepository;
//    private final ImageUploadService imageUploadService;
//
//    @Override
//    public List<Attachment> save(final Article article, final String content) {
//
//        log.debug("Image Save Start");
//
//        final List<ImageExtractElement> elements = imageDataPreProcessor.extract(content);
//        final List<Attachment> attachmentList = new ArrayList<>();
//
//        for (ImageExtractElement element : elements) {
//
//            final byte[] decodeData = this.base64Decode(element.getImageSrc());
//            final String size = String.valueOf(decodeData.length);
//            final String fileName = imageUploadService.upload(decodeData);
//
//            final ImageDto.CreateInfo createInfo = ImageDto.CreateInfo
//                    .builder()
//                    .name(fileName)
//                    .url(resourcePath + fileName)
//                    .properties(element.getProperties())
//                    .size(size)
//                    .build();
//
//            final Attachment savedAttachment = attachmentRepository.save(
//                    new Attachment(article, createInfo));
//
//            attachmentList.add(savedAttachment);
//        }
//
//        return attachmentList;
//    }
//
//    private byte[] base64Decode(String imageSrc) {
//        return Base64.getDecoder()
//                .decode(imageSrc);
//    }
//
////    private String saveImageInLocalEnv(final byte[] image) {
////
////        // TODO 웹서버로 파일 올리기 수행하여야 함.
////
////        final String fileName = getFileName(image) + PNG;
////        final Path folder = Paths.get(resourcePath);
////
////        try {
////
////            if (!Files.exists(folder)) {
////                Files.createDirectory(folder);
////            }
////
////            Files.write(Paths.get(resourcePath + fileName), image);
////
////        } catch (IOException e) {
////
////            log.error("Image 저장 도중 에러 발생 : {}", e.getMessage());
////
////        }
////
////        return fileName;
////    }
//
////    private String getFileName(byte[] image) {
////
////        String uuid = UUID.nameUUIDFromBytes(image).toString().replaceAll(HYPHEN, SPACE);
////        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
////
////        return (uuid + HYPHEN + now);
////    }
//}
