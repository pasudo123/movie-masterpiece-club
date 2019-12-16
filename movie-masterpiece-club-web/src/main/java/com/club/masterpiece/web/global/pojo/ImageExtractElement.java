package com.club.masterpiece.web.global.pojo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by pasudo123 on 2019-12-15
 * Email: oraedoa@gmail.com
 **/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ImageExtractElement {

    private static final int SECOND_INDEX = 1;
    private static final String COMMA = ",";

    private String src;
    @Getter private String style;
    @Getter private String width;

    @Builder
    public ImageExtractElement(String src, String style, String width) {
        this.src = src;
        this.style = style;
        this.width = width;
    }

    public String getImageSrc(){

        if(!src.contains(COMMA)) {
            return src;
        }

        return src.split(COMMA)[SECOND_INDEX];
    }
}
