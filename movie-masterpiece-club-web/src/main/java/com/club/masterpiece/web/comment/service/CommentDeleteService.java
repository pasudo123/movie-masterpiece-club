package com.club.masterpiece.web.comment.service;

import com.club.masterpiece.web.comment.dto.CommentDto;
import com.club.masterpiece.web.comment.dto.ReplyDto;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentDeleteService {


    CommentDto.OneResponse updateCommentStatusActiveToDelete(String commentId);

    ReplyDto.OneResponse updateReplyStatusActiveToDelete(String replyId);
}
