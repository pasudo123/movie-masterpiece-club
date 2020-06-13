package com.club.masterpiece.web.pojo;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class CustomImageData {
    private static final String WHITE_SPACE = " ";
    private final String src;
    private final String style;
    private final String width;

    public CustomImageData(final String src) {
        this(src, Strings.EMPTY, Strings.EMPTY);
    }

    public CustomImageData(final String src, final String style, final String width) {
        this.src = src;
        this.style = style;
        this.width = width;
    }

    public String toImageTag(){
        final StringBuilder tagBuilder = new StringBuilder();
        tagBuilder.append("<img").append(WHITE_SPACE);
        tagBuilder.append("src=\"").append(src).append("\"").append(WHITE_SPACE);
        tagBuilder.append("style=\"").append(style).append("\"").append(WHITE_SPACE);
        tagBuilder.append("width=\"").append(width).append("\"").append(WHITE_SPACE).append("/>");
        return tagBuilder.toString();
    }
}
