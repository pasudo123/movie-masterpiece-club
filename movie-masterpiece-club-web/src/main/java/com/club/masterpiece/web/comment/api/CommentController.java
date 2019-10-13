package com.club.masterpiece.web.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("{commentId}")
    public ResponseEntity<Object> updateComment(@PathVariable("commentId") String commentId) {



        return null;
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable("commentId") String commentId) {



        return null;
    }

}
