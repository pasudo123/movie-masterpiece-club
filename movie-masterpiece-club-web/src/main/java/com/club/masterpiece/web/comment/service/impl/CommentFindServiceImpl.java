package com.club.masterpiece.web.comment.service.impl;

import com.club.masterpiece.web.comment.dto.CommentDto;
import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.exception.EmptyResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentFindServiceImpl implements CommentFindService {

    private final CommentRepository commentRepository;

    @Override
    public CommentDto.ListResponse findAllByArticleId(final String articleId) {

        List<Comment> commentList = commentRepository.findAllByArticleArticleIdAndCommentNullOrderByRegDateAsc(articleId);

        return new CommentDto.ListResponse(commentList);
    }

    @Override
    public CommentDto.ListResponse findAllReplyByCommentId(final String commentId) {

        Comment comment = commentRepository.findOneByCommendId(commentId)
                .orElseThrow(() -> new EmptyResultException("Comment is Not Found."));

        List<Comment> replyList = comment.getReplyList();

        return new CommentDto.ListResponse(replyList);
    }
}
