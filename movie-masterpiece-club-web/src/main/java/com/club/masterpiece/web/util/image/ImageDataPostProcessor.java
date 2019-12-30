package com.club.masterpiece.web.util.image;

import com.club.masterpiece.common.article.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by pasudo123 on 2019-12-30
 * Email: oraedoa@gmail.com
 **/
@Component
public class ImageDataPostProcessor {

    @Value("${keyword.image}")
    private String imageKeyword;

    public String createImageTag(final String url, final Map<String, String> properties) {

        StringJoiner joiner = new StringJoiner(" ", "<", ">");
        joiner.add("img");
        joiner.add("src=\"data:image/png;base64," + url + "\"");

        for(String key : properties.keySet()) {
            joiner.add(key + "=\"" + properties.get(key) + "\"");
        }

        return joiner.toString();
    }

    public String createImageIncludeContent(final ArticleDto.OneResponse dto, final List<String> convertContentList) {

        final String[] partialContents = dto.getContent().split(imageKeyword);

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < partialContents.length; i++) {

            builder.append(partialContents[i]);

            if((partialContents.length - 1) == i) {
                continue;
            }

            builder.append(convertContentList.get(i));
        }

        return builder.toString();
    }

}