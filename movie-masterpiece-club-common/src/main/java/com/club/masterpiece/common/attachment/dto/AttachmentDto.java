package com.club.masterpiece.common.attachment.dto;

import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.common.attachment.model.AttachmentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AttachmentDto {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateImageRequest {
        private String name;
        private AttachmentType type = AttachmentType.IMAGE;
        private String url;
        private String size;

        public CreateImageRequest(final String name,
                                  final String url,
                                  final String size) {
            this.name = name;
            this.url = url;
            this.size = size;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Response {
        private AttachmentType type;
        private String name;
        private String url;
        private Long size;

        public Response(Attachment attachment){
            this.type = attachment.getType();
            this.name = attachment.getName();
            this.url = attachment.getUrl();
            this.size = attachment.getSize();
        }
    }
}
