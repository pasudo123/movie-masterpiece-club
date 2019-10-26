package com.club.masterpiece.web.article.api;

import com.club.masterpiece.web.article.dto.ArticleDto;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.article.service.ArticleDeleteService;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.article.service.ArticleUpdateService;
import com.club.masterpiece.web.comment.dto.CommentDto;
import com.club.masterpiece.web.comment.dto.ReplyDto;
import com.club.masterpiece.web.comment.service.CommentCreateService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.config.security.SecurityOAuth2User;
import com.club.masterpiece.web.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/gulagbu-api/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;
    private final ArticleUpdateService articleUpdateService;
    private final ArticleDeleteService articleDeleteService;

    private final CommentCreateService commentCreateService;
    private final CommentFindService commentFindService;

    @PostMapping
    public ResponseEntity<ArticleDto.OneResponse> createArticle(@AuthenticationPrincipal SecurityOAuth2User user,
                                                                @RequestBody @Valid ArticleDto.CreateRequest dto,
                                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        ArticleDto.OneResponse response = articleCreateService.create(user.getUser(), dto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("{articleId}/comment")
    public ResponseEntity<CommentDto.OneResponse> createComment(@AuthenticationPrincipal SecurityOAuth2User user,
                                                                @PathVariable("articleId") String articleId,
                                                                @RequestBody @Valid CommentDto.CreateRequest dto,
                                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        CommentDto.OneResponse response = commentCreateService.createParentComment(user.getUser(), articleId, dto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("{articleId}/comment/{commentId}/reply")
    public ResponseEntity<ReplyDto.OneResponse> createReply(@AuthenticationPrincipal SecurityOAuth2User user,
                                                            @PathVariable("articleId") String articleId,
                                                            @PathVariable("commentId") String commentId,
                                                            @RequestBody @Valid CommentDto.CreateRequest dto,
                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        ReplyDto.OneResponse response = commentCreateService.createChildComment(user.getUser(), articleId, commentId, dto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ArticleDto.ListResponse> findAll() {

        ArticleDto.ListResponse response = articleFindService.findAll();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleDto.OneResponse> findOneById(@PathVariable("articleId") String articleId) {

        ArticleDto.OneResponse response = articleFindService.findOneById(articleId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{articleId}/comment")
    public ResponseEntity<CommentDto.ListResponse> findAllByArticleId(@PathVariable("articleId") String articleId) {

        CommentDto.ListResponse response = commentFindService.findAllByArticleId(articleId);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{articleId}")
    public ResponseEntity<ArticleDto.OneResponse> updateOneById(@PathVariable("articleId") String articleId,
                                                                @RequestBody ArticleDto.UpdateRequest dto,
                                                                @Valid BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        return ResponseEntity.ok().body(articleUpdateService.updateOneById(articleId, dto));
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<Map<String, Boolean>> deleteOneById(@PathVariable("articleId") String articleId) {

        return ResponseEntity.ok().body(articleDeleteService.deleteOneById(articleId));
    }
}