package com.club.masterpiece.web.image.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.global.pojo.ImageExtractElement;
import com.club.masterpiece.web.image.service.ImageSaveService;
import com.club.masterpiece.web.util.ImageDataExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Created by pasudo123 on 2019-12-14
 * Email: oraedoa@gmail.com
 **/
@Slf4j
@Service
@Qualifier("ImageLocalSaveServiceImpl")
@RequiredArgsConstructor
public class ImageLocalSaveServiceImpl implements ImageSaveService {

    @Value("${image.root-path}")
    private String rootPath;
    private static final String HYPHEN = "-";
    private static final String SPACE = "";
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private static final String PNG = ".png";

    private final ImageDataExtractor imageDataExtractor;

    @Override
    public List<String> saveImage(User user, ArticleDto.CreateRequest dto) {

        log.info("Local Image Save Start");

        final List<ImageExtractElement> elements = imageDataExtractor.extract(dto.getContent());
        final List<byte[]> images = new ArrayList<>();

        for (ImageExtractElement element : elements) {
            images.add(
                    base64Decode(element.getImageSrc())
            );
        }

        final List<String> imagePaths = new ArrayList<>();

        for (byte[] image : images) {
            imagePaths.add(save(image));
        }

        return imagePaths;
    }

    private byte[] base64Decode(String imageSrc) {
        return Base64.getDecoder()
                .decode(imageSrc);
    }

    private String save(final byte[] image) {

        final String fileName = getFileName(image) + PNG;
        final Path folder = Paths.get(rootPath);

        try {

            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
            Files.write(Paths.get(fileName), image);

        } catch (IOException e) {

            log.error("Image 저장 도중 에러 발생 : {}", e.getMessage());

        }

        return fileName;
    }

    private String getFileName(byte[] image) {

        String uuid = UUID.nameUUIDFromBytes(image).toString().replaceAll(HYPHEN, SPACE);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        return (rootPath + uuid + now);
    }
}
