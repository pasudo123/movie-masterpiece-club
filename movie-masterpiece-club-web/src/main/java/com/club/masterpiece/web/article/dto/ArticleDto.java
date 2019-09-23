package com.club.masterpiece.web.article.dto;

import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.model.ArticleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class ArticleDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class createRequest{

        @NotBlank(message = "ArticleContent Should not be blank.")
        private String articleContent;

        @NotNull(message = "ArticleType Should not be null.")
        private ArticleType articleType;

    }

    @Getter
    public static class OneResponse{

        private String id;
        private String content;
        private ArticleType type;
        private LocalDate registerDate;

        @Builder
        public OneResponse(Article article){
            this.id = article.getArticleId();
            this.content = article.getContent();
            this.type = article.getType();
            this.registerDate = article.getRegDate().toLocalDate();
        }
    }

}
