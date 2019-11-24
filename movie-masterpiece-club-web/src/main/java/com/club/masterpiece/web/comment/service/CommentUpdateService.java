package com.club.masterpiece.web.comment.service;

import com.club.masterpiece.common.comment.dto.CommentDto;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentUpdateService {

    public CommentDto.OneResponse   updateContent(String commentId, CommentDto.UpdateRequest dto);

}
