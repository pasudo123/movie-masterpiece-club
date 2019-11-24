package com.club.masterpiece.web.comment.api;

import com.club.masterpiece.common.comment.dto.CommentDto;
import com.club.masterpiece.common.comment.dto.ReplyDto;
import com.club.masterpiece.web.comment.service.CommentDeleteService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.comment.service.CommentUpdateService;
import com.club.masterpiece.web.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    private final CommentFindService commentFindService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;

    @GetMapping("{commentId}/reply")
    public ResponseEntity<CommentDto.ListResponse> getReplyByCommentId(@PathVariable("commentId") String commentId) {

        return ResponseEntity.ok().body(commentFindService.findAllReplyByCommentId(commentId));
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentDto.OneResponse> updateCommentContent(@PathVariable("commentId") String commentId,
                                                                       @RequestBody @Valid CommentDto.UpdateRequest dto,
                                                                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        return ResponseEntity.ok().body(commentUpdateService.updateContent(commentId, dto));
    }

    @PutMapping("reply/{replyId}")
    public ResponseEntity<CommentDto.OneResponse> updateReplyContent(@PathVariable("replyId") String replyId,
                                                                     @RequestBody @Valid CommentDto.UpdateRequest dto,
                                                                     BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomValidationException("Valid Exception.", bindingResult);
        }

        return ResponseEntity.ok().body(commentUpdateService.updateContent(replyId, dto));
    }

    @PutMapping("{commentId}/status")
    public ResponseEntity<CommentDto.OneResponse> updateCommentStatus(@PathVariable("commentId") String commentId) {

        return ResponseEntity.ok().body(commentDeleteService.updateCommentStatusActiveToDelete(commentId));
    }

    @PutMapping("reply/{replyId}/status")
    public ResponseEntity<ReplyDto.OneResponse> updateReplyStatus(@PathVariable("replyId") String replyId) {

        return ResponseEntity.ok().body(commentDeleteService.updateReplyStatusActiveToDelete(replyId));
    }
}
