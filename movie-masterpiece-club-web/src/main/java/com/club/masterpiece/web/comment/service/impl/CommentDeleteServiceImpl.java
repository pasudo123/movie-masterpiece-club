package com.club.masterpiece.web.comment.service.impl;

import com.club.masterpiece.web.comment.dto.CommentDto;
import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import com.club.masterpiece.web.comment.service.CommentDeleteService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.exception.EmptyResultException;
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
    private final CommentRepository commentRepository;

    @Override
    public CommentDto.OneResponse updateStatusActiveToDelete(final String commentId) {

        Comment comment = commentFindService.fineOneEntityByCommentId(commentId);
        comment.updateActive2Delete();

        return new CommentDto.OneResponse(comment);
    }
}
