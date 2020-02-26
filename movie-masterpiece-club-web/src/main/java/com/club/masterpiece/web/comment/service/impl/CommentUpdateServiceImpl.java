package com.club.masterpiece.web.comment.service.impl;

import com.club.masterpiece.common.comment.dto.CommentDto;
import com.club.masterpiece.common.comment.model.Comment;
import com.club.masterpiece.common.comment.repository.CommentRepository;
import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class CommentUpdateServiceImpl implements CommentUpdateService {

    private final CommentFindService commentFindService;

    @UpdatableState
    @Override
    public CommentDto.OneResponse updateContent(final String commentId, final CommentDto.UpdateRequest dto) {

        Comment comment = commentFindService.findOneEntityByCommentId(commentId);
        comment.updateContent(dto);

        return new CommentDto.OneResponse(comment);
    }
}
