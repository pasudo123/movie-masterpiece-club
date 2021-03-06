package com.club.masterpiece.web.comment.service;

import com.club.masterpiece.common.comment.dto.CommentDto;
import com.club.masterpiece.common.comment.model.Comment;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentFindService {

    Comment findOneEntityByCommentId(String commentId);

    CommentDto.ListResponse findAllByArticleId(String articleId);

    CommentDto.ListResponse findAllReplyByCommentId(String commentId);

}
