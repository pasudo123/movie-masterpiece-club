package com.club.masterpiece.web.util.image;

import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.web.pojo.CustomImageData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ImageDataUtil {

    private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<img src\\s*=\\s*\\\\*\"(.+?)\\\\*\"\\s*>");
    private static final Pattern IMAGE_SRC_PATTERN = Pattern.compile("src(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_STYLE_PATTERN = Pattern.compile("style(\\s)?=\"(.*?)\"");
    private static final Pattern IMAGE_WIDTH_PATTERN = Pattern.compile("width(\\s)?=\"(.*?)\"");
    private static final String DOUBLE_QUOTATION = "\"";

    public String changeImageSrcIfPossible(final String content, List<ImageDto.CreateResponse> images){
        final Matcher matcher = buildImageTagMatcher(content);
        int currentIndex = 0;

        while(matcher.find()){
            final String imageTag = matcher.group();
            final String styleValue = this.extractStringByPattern(imageTag, IMAGE_STYLE_PATTERN);
            final String widthValue = this.extractStringByPattern(imageTag, IMAGE_WIDTH_PATTERN);
            final CustomImageData customImageData = new CustomImageData(images.get(currentIndex++).getUrl(), styleValue, widthValue);
            content.replaceFirst(IMAGE_TAG_PATTERN.toString(), customImageData.toImageTag());
        }

        return content;
    }

    public List<CustomImageData> extractImage(final String content) {

        final List<CustomImageData> imageDataList = new ArrayList<>();

        final Matcher matcher = buildImageTagMatcher(content);

        while(matcher.find()) {
            final String imageTag = matcher.group();
            final String srcValue = this.extractStringByPattern(imageTag, IMAGE_SRC_PATTERN);
            imageDataList.add(new CustomImageData(srcValue));
        }

        return imageDataList;
    }

    private Matcher buildImageTagMatcher(final String content){
        return IMAGE_TAG_PATTERN.matcher(content);
    }

    /**
     * 이미지 태그에서
     * - 경로 값을 추출한다. (데이터 추출)
     * - 스타일 값을 추출한다.
     * - 너비 값을 추출한다.
     * @param imageTag
     * @return
     */
    private String extractStringByPattern(final String imageTag, final Pattern pattern) {
        final Matcher matcher = pattern.matcher(imageTag);
        return (!matcher.find()) ? Strings.EMPTY : subStringDoubleQuotation(matcher.group());
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
