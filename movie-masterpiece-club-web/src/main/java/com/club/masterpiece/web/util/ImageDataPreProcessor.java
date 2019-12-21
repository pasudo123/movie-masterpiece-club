package com.club.masterpiece.web.util;

import com.club.masterpiece.common.attachment.dto.ImageExtractElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Component
@Slf4j
public class ImageDataPreProcessor {

    private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");
    private static final Pattern IMAGE_SRC_PATTERN = Pattern.compile("src(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_STYLE_PATTERN = Pattern.compile("style(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_WIDTH_PATTERN = Pattern.compile("width(\\s)?=\"(.*?)\"");
    private static final String DOUBLE_QUOTATION = "\"";

    private final ObjectMapper mapper;
    private static final String STYLE = "style";
    private static final String WIDTH = "width";

    public List<ImageExtractElement> extract(final String articleContent) {

        final List<ImageExtractElement> imageList = new ArrayList<>();

        final Matcher matcher = IMAGE_TAG_PATTERN.matcher(articleContent);

        while(matcher.find()) {

            final String imageTag = matcher.group();

            final String srcValue = this.extractImageSrc(imageTag);
            final String styleValue = this.extractImageStyle(imageTag);
            final String widthValue = this.extractImageWidth(imageTag);
            final String propValue = this.changeImagePropsToJson(styleValue, widthValue);

            final ImageExtractElement element = ImageExtractElement
                    .builder()
                    .src(srcValue)
                    .style(styleValue)
                    .width(widthValue)
                    .properties(propValue)
                    .build();

            imageList.add(element);
        }

        return imageList;
    }

    /**
     * 이미지 추출 요소에서 json 값으로 변환한다.
     * @param styleValue
     * @param widthValue
     * @return
     */
    private String changeImagePropsToJson(final String styleValue, final String widthValue) {

        ObjectNode node = mapper.createObjectNode();
        node.put(STYLE, styleValue);
        node.put(WIDTH, widthValue);

        return node.toString();
    }

    /**
     * 이미지 태그에서 경로 값을 추출한다.
     * @param imageTag
     * @return
     */
    private String extractImageSrc(final String imageTag){

        final Matcher matcher = IMAGE_SRC_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    /**
     * 이미지 태그에서 스타일 값을 추출한다.
     * @param imageTag
     * @return
     */
    private String extractImageStyle(final String imageTag){

        final Matcher matcher = IMAGE_STYLE_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    /**
     * 이미지 태그에서 너비 값을 추출한다.
     * @param imageTag
     * @return
     */
    private String extractImageWidth(final String imageTag) {

        final Matcher matcher = IMAGE_WIDTH_PATTERN.matcher(imageTag);

        if(!matcher.find()) {
            return Strings.EMPTY;
        }

        return subStringDoubleQuotation(matcher.group());
    }

    /**
     * 양쪽의 쌍따옴표를 제거한다.
     * @param line
     * @return
     */
    private String subStringDoubleQuotation(final String line) {

        final int beginIndex = line.indexOf(DOUBLE_QUOTATION) + 1;
        final int lastIndex = line.lastIndexOf(DOUBLE_QUOTATION);

        return line.substring(beginIndex, lastIndex);
    }
}
