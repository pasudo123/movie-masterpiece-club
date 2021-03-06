package com.club.masterpiece.storage.image.service;

import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.storage.image.exception.ImageSaveException;
import com.club.masterpiece.storage.pojo.ImageFile;
import com.club.masterpiece.storage.util.Base64Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageUploadService {

    @Value("${image.file.directory}")
    private String directory;

    @Value("${services.masterpiece-storage-static-url}")
    private String imageUrl;

    private final Base64Converter base64Converter;

    public ImageDto.CreateResponse upload(final ImageDto.CreateRequest createRequest){

        final byte[] byteArray = base64Converter.ImageSrcTagToByte(createRequest.getImageByteData());
        final ImageFile imageFile = ImageFile.create(directory, byteArray);
        final String path = save(imageFile);
        final long size = (long)byteArray.length;

        return new ImageDto.CreateResponse(imageUrl, path, size);
    }

    private String save(final ImageFile imageFile){
        // reference : https://www.baeldung.com/java-base64-image-string
        try {
            final File outputFile = new File(directory + File.separator + imageFile.getFileName());
            log.info("File Saving... : {}", outputFile.getAbsolutePath());
            FileUtils.writeByteArrayToFile(outputFile, imageFile.getBytes());
            return outputFile.getCanonicalPath();
        } catch (IOException e){
            throw new ImageSaveException(String.format("File Save Failed : %s", e.getMessage()));
        }
    }
}
