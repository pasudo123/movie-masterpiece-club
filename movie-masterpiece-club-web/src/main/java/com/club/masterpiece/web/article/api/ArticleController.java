package com.club.masterpiece.web.article.api;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private ArticleCreateService articleCreateService;

    @PostMapping
    public ResponseEntity<ArticleDto.OneResponse> createArticle(@RequestBody @Valid ArticleDto.createRequest dto,
                                        BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        log.debug("content : {}", dto.getArticleContent());
        log.debug("content : {}", dto.getArticleType());

        return ResponseEntity.ok().body(null);
    }

}
