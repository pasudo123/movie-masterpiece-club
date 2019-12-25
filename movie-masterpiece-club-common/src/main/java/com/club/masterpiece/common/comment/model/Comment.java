package com.club.masterpiece.common.comment.model;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.comment.dto.CommentDto;
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
import java.util.stream.Collectors;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Entity
@Table(name = "comment", indexes = {@Index(name = "reg_idx", columnList = "reg_date")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @Column(name = "comment_id", length = 40, nullable = false)
    private String commendId;

    @Column(name = "comment_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE') default 'ACTIVE'")
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_comment_id",
            columnDefinition = "VARCHAR(40)",
            referencedColumnName = "comment_id",
            nullable = true
    )
    private Comment comment;

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "comment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("reg_date asc")
    private List<Comment> replyList = new ArrayList<>();

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            columnDefinition = "VARCHAR(45)",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_user_idx")
    )
    private User user;

    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",
            columnDefinition = "VARCHAR(40)",
            referencedColumnName = "article_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_article_idx")
    )
    private Article article;

    @Builder
    public Comment(String commendId, String content, User user, Article article) {
        this.commendId = commendId;
        this.content = content;
        this.user = user;
        this.article = article;
    }

    public void addNewReply(final Comment reply) {
        reply.setParentComment(this);
        replyList.add(reply);
    }

    private void setParentComment(final Comment parentComment) {
        this.comment = parentComment;
    }

    private void setComment(final Comment comment) {
        this.comment = comment;
        getArticle().addNewComment(this);
    }

    public void updateCommentActiveToDelete(){
        this.activeStatus = ActiveStatus.DELETE;
        getReplyList().forEach(Comment::updateReplyActiveToDelete);
    }

    public void updateReplyActiveToDelete() {
        this.activeStatus = ActiveStatus.DELETE;
    }

    public boolean isActive(){
        return (this.activeStatus == ActiveStatus.ACTIVE);
    }

    public List<Comment> getActiveReply() {
        return getReplyList()
                .stream()
                .filter(Comment::isActive)
                .collect(Collectors.toList());
    }

    public void updateContent(final CommentDto.UpdateRequest dto) {
        this.content = dto.getContent();
    }
}