package com.club.masterpiece.common.article.dto;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.model.ArticleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class ArticleDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateRequest{

        @NotBlank(message = "Article Title Should not be blank.")
        private String title;

        @NotBlank(message = "Article Content Should not be blank.")
        private String content;

        @NotNull(message = "Article Type Should not be null.")
        private ArticleType type;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRequest {

        @NotBlank(message = "Article Title Should not be blank.")
        private String title;

        @NotBlank(message = "Article Content Should not be blank.")
        private String content;
    }

    @Getter
    public static class OneResponse{

        private String id;
        private String title;
        private String content;
        private LocalDate registerDate;
        private String registerTimeFormat;
        private String createdUserId;
        private String createdName;
        private String createdProfile;

        @SuppressWarnings("Duplicates")
        public OneResponse(Article article){
            this.id = article.getArticleId();
            this.title = article.getTitle();
            this.content = article.getContent();
            this.registerDate = article.getRegDate().toLocalDate();
            this.registerTimeFormat = article.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.createdUserId = article.getUser().getId();
            this.createdName = article.getUser().getUsername();
            this.createdProfile = article.getUser().getProfile();
        }
    }

    @Getter
    public static class ListResponse {

        @JsonProperty("articleList")
        List<OneResponse> list = new ArrayList<>();

        public ListResponse(List<Article> articleList) {

            for(Article article : articleList) {
                list.add(new ArticleDto.OneResponse(article));
            }
        }
    }
}