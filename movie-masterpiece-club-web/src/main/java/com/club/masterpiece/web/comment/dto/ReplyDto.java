package com.club.masterpiece.web.comment.dto;

import com.club.masterpiece.web.comment.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-10-16
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class ReplyDto {

    @Getter
    public static class OneResponse {

        private String parentId;
        private String id;
        private String comment;
        private LocalDate registerDate;
        private String registerTimeFormat;
        private String createdUserId;
        private String createdName;
        private String createdProfile;

        @SuppressWarnings("Duplicates")
        public OneResponse(Comment comment) {
            this.parentId = comment.getComment().getCommendId();
            this.id = comment.getCommendId();
            this.comment = comment.getContent();
            this.registerDate = comment.getRegDate().toLocalDate();
            this.registerTimeFormat = comment.getRegDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
            this.createdUserId = comment.getUser().getId();
            this.createdName = comment.getUser().getUsername();
            this.createdProfile = comment.getUser().getProfile();
        }
    }

    @Getter
    static class ListResponse{

        List<ReplyDto.OneResponse> list = new ArrayList<>();

        ListResponse(List<Comment> commentList) {
            for(Comment comment : commentList) {
                this.list.add(new ReplyDto.OneResponse(comment));
            }
        }
    }
}
