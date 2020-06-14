package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.repository.ArticleRepository;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.exception.EmptyResultException;
import com.club.masterpiece.web.global.pojo.PageRequestDto;
import com.club.masterpiece.web.util.image.ImageDataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleFindServiceImpl implements ArticleFindService {

    private final ArticleRepository articleRepository;
    private final ImageDataUtil imageDataUtil;

    @Transactional(readOnly = true)
    @Override
    public ArticleDto.ListResponse findAll() {

        List<Article> articleList = articleRepository.findAllByOrderByRegDateDesc();

        return new ArticleDto.ListResponse(articleList);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {

        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ArticleDto.OneResponse> findPartialByPage(PageRequestDto dto) {

        return articleRepository.findAll(dto.of()).map(ArticleDto.OneResponse::new);
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleDto.OneResponse findOneById(final String articleId) {

        Article article = articleRepository.findOneById(articleId)
                .orElseThrow(() -> new EmptyResultException(String.format("Article is Not Found. : %s", articleId)));

        /** 첨부파일 미존재 **/
        if(article.getAttachmentList().size() == 0) {
            return new ArticleDto.OneResponse(article);
        }

        /** 첨부파일 존재 **/
        return new ArticleDto.OneResponse(article, imageDataUtil.changeImageSrcIfPossible(
                article.getContent(),
                article.getAttachmentList()));
    }

    @Transactional(readOnly = true)
    @Override
    public Article findEntityById(final String articleId) {

        return articleRepository.findOneById(articleId)
                .orElseThrow(() -> new EmptyResultException(String.format("Article is Not Found. : %s", articleId)));
    }
}
