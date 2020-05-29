package com.club.masterpiece.storage.image.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ImageDto {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateRequest {
        private LocalDateTime currentTime;
        private String imageByteData;

        public CreateRequest(final String imageByteData){
            this.currentTime = LocalDateTime.now();
            this.imageByteData = imageByteData;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateResponse {
        private String imageUrl;

        public CreateResponse(final String imageUrl){
            this.imageUrl = imageUrl;
        }
    }
}
