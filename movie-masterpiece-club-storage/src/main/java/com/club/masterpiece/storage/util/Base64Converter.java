package com.club.masterpiece.storage.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Converter {

    private static final String COMMA = ",";

    public byte[] ImageSrcTagToByte(final String imageSrc){
        final String imageDataBytes = imageSrc.substring(imageSrc.indexOf(COMMA) + 1);
        return Base64.getDecoder().decode(imageDataBytes);
    }
}
