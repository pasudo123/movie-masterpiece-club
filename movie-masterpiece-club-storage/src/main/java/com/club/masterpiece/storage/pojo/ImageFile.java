package com.club.masterpiece.storage.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ImageFile {

    private static final String PNG_EXTENSION = ".png";
    private static final String HYPHEN = "-";
    private static final String DATE_IMAGE_FILE_PREFIX = "yyyyMMddhhmmss_";

    private byte[] bytes;
    private String fileName;
    private String fileRelativePath;

    private ImageFile(final String directory, final byte[] bytes){
        this.init(bytes);
        this.fileRelativePath = directory + fileName;
    }

    public static ImageFile create(final String directory, final byte[] bytes){
        return new ImageFile(directory, bytes);
    }

    private void init(final byte[] bytes){
        this.bytes = bytes;
        final String uuid = UUID.nameUUIDFromBytes(this.bytes).toString().replace(HYPHEN, Strings.EMPTY);
        final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_IMAGE_FILE_PREFIX));
        this.fileName = (time + uuid) + PNG_EXTENSION;
    }

    public String getFileInfo(){
        final StringBuilder builder = new StringBuilder();
        builder.append("{fileName : ");
        builder.append(fileName);
        builder.append(", ");
        builder.append("{filePath : ");
        builder.append(fileRelativePath);
        builder.append(" }");
        return builder.toString();
    }
}
