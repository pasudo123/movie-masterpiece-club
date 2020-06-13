package com.club.masterpiece.web.pojo;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.ArticleType;
import com.club.masterpiece.common.user.model.User;
import lombok.Getter;

@Getter
public class CustomArticleData {
    private String id;
    private String title;
    private String content;
    private ArticleType type;
    private User createdBy;

    public CustomArticleData(final String id,
                             final ArticleDto.CreateRequest dto,
                             final User createdBy) {
        this.id = id;
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.type = dto.getType();
        this.createdBy = createdBy;
    }
}


