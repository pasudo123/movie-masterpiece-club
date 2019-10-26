package com.club.masterpiece.web.article.service.impl;

import com.club.masterpiece.web.article.repository.ArticleRepository;
import com.club.masterpiece.web.article.service.ArticleDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleDeleteServiceImpl implements ArticleDeleteService {

    private final ArticleRepository articleRepository;

    @Override
    public Map<String, Boolean> deleteOneById(final String articleId) {

        articleRepository.deleteById(articleId);

        return new HashMap<String, Boolean>(){{
            put(String.format("id : %s is deleted", articleId), Boolean.TRUE);
        }};
    }
}
