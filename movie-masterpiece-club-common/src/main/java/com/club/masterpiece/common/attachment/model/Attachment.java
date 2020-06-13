package com.club.masterpiece.common.attachment.model;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.global.type.ActiveStatus;
import lombok.AccessLevel;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('IMAGE', 'VIDEO') '첨부파일 타입 선정(이미지, 동영상)'", nullable = false)
    private AttachmentType type;

    @Column(name = "name", columnDefinition = "VARCHAR(60)", nullable = false)
    private String name;

    @Column(name = "url", columnDefinition = "TEXT '첨부파일 경로'", nullable = false)
    private String url;

    @Column(name = "size", columnDefinition = "INT '첨부파일 사이즈'", nullable = false)
    private Long size;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_id",
            columnDefinition = "VARCHAR(40)",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_attachment_article_idx")
    )
    private Article article;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE') default 'ACTIVE'", nullable = false)
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

//    public Attachment(final Article article, final ImageDto.CreateInfo createInfo) {
//        this.article = article;
//        this.type = createInfo.getType();
//        this.url = createInfo.getUrl();
//    }

    public void updateActiveToDelete() {
        this.activeStatus = ActiveStatus.DELETE;
    }

    public boolean isDelete() {
        return (this.activeStatus == ActiveStatus.DELETE);
    }
}
