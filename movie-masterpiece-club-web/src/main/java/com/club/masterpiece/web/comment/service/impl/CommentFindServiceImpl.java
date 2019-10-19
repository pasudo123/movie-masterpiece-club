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
import java.util.stream.Collectors;

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
    public Comment findOneEntityByCommentId(final String commentId) {

        return commentRepository.findOneByCommendId(commentId)
                .orElseThrow(() -> new EmptyResultException(String.format("Comment is Not Found. : %s", commentId)));
    }

    @Override
    public CommentDto.ListResponse findAllByArticleId(final String articleId) {

        List<Comment> commentList = commentRepository.findAllByArticleArticleIdAndCommentNullOrderByRegDateAsc(articleId);

        /** comment status : 'active' 추출 **/
        /** dto 내에서 reply status : 'active' 추출 **/
        return new CommentDto.ListResponse(commentList.stream()
                .filter(Comment::isActive)
                .collect(Collectors.toList()));
    }

    @Override
    public CommentDto.ListResponse findAllReplyByCommentId(final String commentId) {

        Comment comment = commentRepository.findOneByCommendId(commentId)
                .orElseThrow(() -> new EmptyResultException(String.format("Comment is Not Found. : %s", commentId)));

        List<Comment> replyList = comment.getReplyList();

        return new CommentDto.ListResponse(replyList);
    }
}
