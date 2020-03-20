package com.club.masterpiece.web.image.service.impl;

import com.club.masterpiece.web.image.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * https://www.codejava.net/java-se/ftp/java-ftp-file-upload-tutorial-and-example
 * https://www.mysoftkey.com/java/ftp-upload-of-file-using-java/
 *
 * ftp client 를 통한 웹서버에 파일 저장
 */
@Slf4j
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private static final String HYPHEN = "-";
    private static final String WHITE_SPACE = "";
    private static final String PNG = ".png";
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    @Value("${gulagbu-resource.account.id}") private String id;
    @Value("${gulagbu-resource.account.pw}") private String pw;
    @Value("${gulagbu-resource.server}") private String server;
    @Value("${gulagbu-resource.port}") private Integer port;
    @Value("${gulagbu-resource.path}") private String path;

    private String getFileName(byte[] image) {

        String uuid = UUID.nameUUIDFromBytes(image).toString().replaceAll(HYPHEN, WHITE_SPACE);
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        return (nowTime + HYPHEN + uuid) + PNG;
    }

    @Override
    public String upload(byte[] image) {
        return null;
    }
}
