package com.club.masterpiece.web.comment.service.impl;

import com.club.masterpiece.common.comment.dto.CommentDto;
import com.club.masterpiece.common.comment.dto.ReplyDto;
import com.club.masterpiece.common.comment.model.Comment;
import com.club.masterpiece.common.comment.repository.CommentRepository;
import com.club.masterpiece.web.annotation.UpdatableState;
import com.club.masterpiece.web.comment.service.CommentDeleteService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CommentDeleteServiceImpl implements CommentDeleteService {

    private final CommentFindService commentFindService;

    @UpdatableState
    @Override
    public CommentDto.OneResponse updateCommentStatusActiveToDelete(final String commentId) {

        Comment comment = commentFindService.findOneEntityByCommentId(commentId);
        comment.updateCommentActiveToDelete();

        return new CommentDto.OneResponse(comment);
    }

    @UpdatableState
    @Override
    public ReplyDto.OneResponse updateReplyStatusActiveToDelete(final String replyId) {

        Comment reply = commentFindService.findOneEntityByCommentId(replyId);
        reply.updateReplyActiveToDelete();

        return new ReplyDto.OneResponse(reply);
    }
}
