package com.club.masterpiece.common.attachment.dto;

import com.club.masterpiece.common.attachment.model.AttachmentType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by pasudo123 on 2019-12-21
 * Email: oraedoa@gmail.com
 **/
public class ImageDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateInfo {

        private AttachmentType type = AttachmentType.IMAGE;
        private String name;
        private String url;
        private String properties;
        private String size;

        @Builder
        public CreateInfo(String name, String url, String properties, String size) {
            this.name = name;
            this.url = url;
            this.properties = properties;
            this.size = size;
        }
    }
}
