package com.club.masterpiece.common.attachment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ImageDto {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateRequest {
        private String imageByteData;

        public CreateRequest(final String imageByteData){
            this.imageByteData = imageByteData;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateResponse {
        private LocalDateTime now;
        private String url;
        private String path;
        private long size;

        public CreateResponse(final String url, final String path, final long size) {
            this.now = LocalDateTime.now();
            this.url = url;
            this.path = path;
            this.size = size;
        }
    }
}
