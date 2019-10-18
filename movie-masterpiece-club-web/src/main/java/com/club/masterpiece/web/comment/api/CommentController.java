package com.club.masterpiece.web.comment.api;

import com.club.masterpiece.web.comment.dto.CommentDto;
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

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/gulagbu-api/comment")
@Slf4j
public class CommentController {

    private final CommentCreateService commentCreateService;
    private final CommentFindService commentFindService;

    @GetMapping("{commentId}/reply")
    public ResponseEntity<CommentDto.ListResponse> getReplyByCommentId(@PathVariable("commentId") String commentId) {

        CommentDto.ListResponse response = commentFindService.findAllReplyByCommentId(commentId);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{commentId}")
    public ResponseEntity<Object> updateComment(@AuthenticationPrincipal SecurityOAuth2User user,
                                                @PathVariable("commentId") String commentId,
                                                @RequestBody @Valid CommentDto.CreateRequest dto,
                                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        return null;
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable("commentId") String commentId) {



        return null;
    }

}
