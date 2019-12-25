package com.club.masterpiece.common.attachment.model;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.common.attachment.dto.ImageExtractElement;
import com.club.masterpiece.common.global.type.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-12-21
 * Email: oraedoa@gmail.com
 **/
@Getter
@Entity
@Table(name = "attachment",
        indexes = {
                @Index(name = "reg_idx", columnList = "reg_date")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('IMAGE', 'VIDEO') '첨부파일 타입 선정(이미지, 동영상)'", nullable = false)
    private AttachmentType type;

    @Column(name = "name", columnDefinition = "VARCHAR(60) '이름'", length = 60, nullable = false)
    private String name;

    @Column(name = "url", columnDefinition = "TEXT '첨부파일 경로'", nullable = false)
    private String url;

    @Column(name = "properties", columnDefinition = "TEXT '첨부파일 속성'", nullable = false)
    private String properties;

    @Column(name = "size", columnDefinition = "VARCHAR(20) '파일 사이즈'", nullable = false)
    private String size;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_id",
            columnDefinition = "VARCHAR(40)",
            referencedColumnName = "article_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_attachment_article_idx")
    )
    private Article article;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE') default 'ACTIVE'", nullable = false)
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

    public Attachment(final Article article, final ImageDto.CreateInfo createInfo) {
        this.article = article;
        this.type = createInfo.getType();
        this.name = createInfo.getName();
        this.url = createInfo.getUrl();
        this.properties = createInfo.getProperties();
        this.size = createInfo.getSize();
    }

    public void setArticle(final Article article) {
        this.article = article;
    }

    public void updateActiveToDelete() {
        this.activeStatus = ActiveStatus.DELETE;
    }

    public boolean isDelete() {
        return (this.activeStatus == ActiveStatus.DELETE);
    }
}
