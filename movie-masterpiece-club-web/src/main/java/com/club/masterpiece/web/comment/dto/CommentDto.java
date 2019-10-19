package com.club.masterpiece.web.comment.dto;

import com.club.masterpiece.web.comment.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class CommentDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateRequest {

        @NotBlank(message = "Content Should not be blank.")
        private String content;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRequest {

        @NotBlank(message = "Content Should not be blank.")
        private String content;

    }

    @Getter
    public static class OneResponse {

        private String id;
        private String comment;
        private LocalDate registerDate;
        private String createdUserId;
        private String createdName;
        private String createdProfile;
        private ReplyDto.ListResponse reply;

        @SuppressWarnings("Duplicates")
        public OneResponse(Comment comment) {
            this.id = comment.getCommendId();
            this.comment = comment.getContent();
            this.registerDate = comment.getRegDate().toLocalDate();
            this.createdUserId = comment.getUser().getId();
            this.createdName = comment.getUser().getUsername();
            this.createdProfile = comment.getUser().getProfile();
            this.reply = new ReplyDto.ListResponse(comment.getActiveReply());
        }
    }

    @Getter
    public static class ListResponse {

        List<CommentDto.OneResponse> commentList = new ArrayList<>();

        public ListResponse(List<Comment> commentList) {
            for(Comment comment : commentList) {
                this.commentList.add(new CommentDto.OneResponse(comment));
            }
        }
    }
}
