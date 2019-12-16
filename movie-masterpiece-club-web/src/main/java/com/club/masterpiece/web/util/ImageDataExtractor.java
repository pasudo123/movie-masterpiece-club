package com.club.masterpiece.web.util;

import com.club.masterpiece.web.global.pojo.ImageExtractElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pasudo123 on 2019-12-14
 * Email: oraedoa@gmail.com
 **/
@Component
@Slf4j
public class ImageDataExtractor {

    private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");
    private static final Pattern IMAGE_SRC_PATTERN = Pattern.compile("src(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_STYLE_PATTERN = Pattern.compile("style(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_WIDTH_PATTERN = Pattern.compile("width(\\s)?=\"(.*?)\"");
    private static final String DOUBLE_QUOTATION = "\"";

    public List<ImageExtractElement> extract(final String articleContent) {

        final List<ImageExtractElement> imageList = new ArrayList<>();

        final Matcher matcher = IMAGE_TAG_PATTERN.matcher(articleContent);

        while(matcher.find()) {

            final String imageTag = matcher.group();

            final String srcValue = this.extractImageSrc(imageTag);
            final String styleValue = this.extractImageStyle(imageTag);
            final String widthValue = this.extractImageWidth(imageTag);

            final ImageExtractElement element = ImageExtractElement
                    .builder()
                    .src(srcValue)
                    .style(styleValue)
                    .width(widthValue)
                    .build();

            imageList.add(element);
        }

        return imageList;
    }

    private String extractImageSrc(final String imageTag){

        final Matcher matcher = IMAGE_SRC_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    private String extractImageStyle(final String imageTag){

        final Matcher matcher = IMAGE_STYLE_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    private String extractImageWidth(final String imageTag) {

        final Matcher matcher = IMAGE_WIDTH_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    private String subStringDoubleQuotation(final String line) {

        final int beginIndex = line.indexOf(DOUBLE_QUOTATION) + 1;
        final int lastIndex = line.lastIndexOf(DOUBLE_QUOTATION);

        return line.substring(beginIndex, lastIndex);
    }
}
