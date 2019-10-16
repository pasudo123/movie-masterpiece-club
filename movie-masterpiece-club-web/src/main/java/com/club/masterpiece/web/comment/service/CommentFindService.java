package com.club.masterpiece.web.comment.service;

import com.club.masterpiece.web.comment.dto.CommentDto;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentFindService {

    CommentDto.ListResponse findAllByArticleId(String articleId);

    CommentDto.ListResponse findAllReplyByCommentId(String commentId);

}
