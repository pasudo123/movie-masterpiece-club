package com.club.masterpiece.web.article.dto;

import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.model.ArticleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    public static class createRequest{

        @NotBlank(message = "Article Title Should not be blank.")
        private String title;

        @NotBlank(message = "Article Content Should not be blank.")
        private String content;

        @NotNull(message = "Article Type Should not be null.")
        private ArticleType type;

    }

    @Getter
    public static class OneResponse{

        private String id;
        private String title;
        private String content;
        private LocalDate registerDate;
        private String createdName;
        private String createdProfile;

        public OneResponse(Article article){
            this.id = article.getArticleId();
            this.title = article.getTitle();
            this.content = article.getContent();
            this.registerDate = article.getRegDate().toLocalDate();
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
