package com.club.masterpiece.web.comment.service;

import com.club.masterpiece.common.comment.dto.CommentDto;
import com.club.masterpiece.common.comment.dto.ReplyDto;
import com.club.masterpiece.common.user.model.User;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentCreateService {

    CommentDto.OneResponse createParentComment(User user, String articleId, CommentDto.CreateRequest dto);

    ReplyDto.OneResponse createChildComment(User user, String articleId, String commentId, CommentDto.CreateRequest dto);

}
