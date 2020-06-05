package com.club.masterpiece.storage.image.dto;

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
        private String path;
        private int size;

        public CreateResponse(String path, int size) {
            this.now = LocalDateTime.now();
            this.path = path;
            this.size = size;
        }
    }
}
