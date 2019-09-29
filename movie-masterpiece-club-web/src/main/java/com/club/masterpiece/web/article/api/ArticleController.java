package com.club.masterpiece.web.article.api;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;

    @PostMapping
    public ResponseEntity<ArticleDto.OneResponse> createArticle(
            @RequestBody @Valid ArticleDto.createRequest dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        ArticleDto.OneResponse response = articleCreateService.create(dto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {

        ArticleDto.ListResponse response = articleFindService.findAll();

        return ResponseEntity.ok().body(response);
    }
}
