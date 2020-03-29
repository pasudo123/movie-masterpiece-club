package com.club.masterpiece.storage.pojo;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
public class ImageFile {

    private static final String PNG_EXTENSION = ".png";
    private byte[] bytes;
    private String fileName;

    private ImageFile(final byte[] bytes){
        this.bytes = bytes;
    }

    public static ImageFile create(final byte[] bytes){
        ImageFile file = new ImageFile(bytes);
        file.initFileName();
        return file;
    }

    private void initFileName(){
        final String uuid = UUID.nameUUIDFromBytes(bytes).toString().replace("-", Strings.EMPTY);
        final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.fileName = (time + "-" + uuid) + PNG_EXTENSION;
    }
}
