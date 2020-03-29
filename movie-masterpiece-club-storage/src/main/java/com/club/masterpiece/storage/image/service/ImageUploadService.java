package com.club.masterpiece.storage.image.service;

import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.storage.pojo.ImageFile;
import com.club.masterpiece.storage.pojo.ImageTag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 이미지 파일 업로드 수행.
 */
@Service
@Slf4j
public class ImageUploadService {

    private final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");

    @Value("${image.file.directory}")
    private String directory;

    @Value("${image.file.marker}")
    private String imageFileMarker;

    public String removeImageTagIfPossible(String content){
        return content.replaceAll(String.valueOf(IMAGE_TAG_PATTERN), imageFileMarker);
    }

    public void process(String content){

        final Matcher matcher = IMAGE_TAG_PATTERN.matcher(content);
        final Queue<ImageTag> imageTags = new LinkedList<>();
        while(matcher.find()){
            imageTags.add(ImageTag.create(matcher.group()));
    }

        while(!imageTags.isEmpty()){
            final ImageTag imageTag = imageTags.poll();
            final byte[] decodeData = imageTag.decodeBase64();
            final String size = String.valueOf(decodeData.length);

            ImageFile file = ImageFile.create(decodeData);
            save(directory, file.getFileName(), file.getBytes());
        }
    }

    /**
     * reference : https://www.baeldung.com/java-base64-image-string
     */
    private void save(String dest, String fileName, final byte[] bytes){
        try {
            FileUtils.writeByteArrayToFile(new File(dest, fileName), bytes);
        } catch (IOException e) {
            log.error("File Save Failed : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
