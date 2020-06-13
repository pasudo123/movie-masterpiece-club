package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.article.service.ArticleUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleUpdateServiceImpl implements ArticleUpdateService {

//    private final ImageSaveService imageSaveService;
//    private final ImageConverter imageConverter;
    private final ArticleFindService articleFindService;

    @UpdatableState
    @Override
    public ArticleDto.OneResponse updateOneById(String articleId, ArticleDto.UpdateRequest dto) {

        final Article foundArticle = articleFindService.findEntityById(articleId);
//        foundArticle.updateArticle(
//                imageConverter.convertImageTagToKeywordOnUpdate(dto));

        /** foundArticle 을 통해 조회하는 첨부파일 active 변경. **/
        foundArticle.changeDeleteOnAttachmentList();

//        /** imagw 저장 이후에 영속성 컨텍스트를 통해서 새롭게 획득한 첨부파일 추가. **/
//        final List<Attachment> attachmentList = imageSaveService.save(foundArticle, dto.getContent());
//        foundArticle.updateAttachmentList(attachmentList);

        return new ArticleDto.OneResponse(foundArticle);
    }
}
