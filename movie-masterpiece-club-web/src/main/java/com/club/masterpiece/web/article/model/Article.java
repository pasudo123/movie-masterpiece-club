package com.club.masterpiece.web.article.model;

import com.club.masterpiece.web.global.type.ActiveStatus;
import com.club.masterpiece.web.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Entity
@Table(name = "article",
        indexes = {
                @Index(name = "reg_idx", columnList = "reg_date"),
                @Index(name = "mod_idx", columnList = "mod_date")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @Column(name = "article_id", length = 40, nullable = false)
    private String articleId;

    @Column(name = "article_title", nullable = false, length = 50)
    private String title;

    @Column(name = "article_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "article_type", columnDefinition = "ENUM('GENERAL', 'OFFICIAL')", nullable = false)
    private ArticleType type;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE') default 'ACTIVE'")
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            columnDefinition = "VARCHAR(45)",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_article_user_idx"))
    private User user;

    @Builder
    public Article(String articleId, String title, String content, ArticleType type, User user) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
    }
}
