package com.club.masterpiece.common.article.model;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.common.comment.model.Comment;
import com.club.masterpiece.common.global.type.ActiveStatus;
import com.club.masterpiece.common.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE') default 'ACTIVE'", nullable = false)
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            columnDefinition = "VARCHAR(45)",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_article_user_idx"))
    private User user;

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "article",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(
        targetEntity = Attachment.class,
        mappedBy = "article",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Attachment> attachmentList = new ArrayList<>();

    @Builder
    public Article(String articleId, String title, String content, ArticleType type, User user) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
    }

    public void createAttachmentList(final List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public void updateAttachmentList(final List<Attachment> attachmentList) {
        getAttachmentList().forEach(Attachment::updateActiveToDelete);
        getAttachmentList().addAll(attachmentList);
    }

    public void updateArticle(final ArticleDto.UpdateRequest dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void addNewComment(final Comment comment) {
        this.commentList.add(comment);
    }
}
