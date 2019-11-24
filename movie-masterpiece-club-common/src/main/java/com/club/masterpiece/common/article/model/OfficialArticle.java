package com.club.masterpiece.common.article.model;

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
@Table(name = "official_article", indexes = @Index(name = "mod_idx", columnList = "mod_date"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class OfficialArticle {

    @Id
    @Column(name = "official_id", length = 40, nullable = false)
    private String officialArticleId;

    @Column(name = "official_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "user_id", length = 40, nullable = false)
    private String userId;

    @Builder
    public OfficialArticle(String officialArticleId, String content, String userId) {
        this.officialArticleId = officialArticleId;
        this.content = content;
        this.userId = userId;
    }
}
