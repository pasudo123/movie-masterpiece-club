package com.club.masterpiece.web.comment.service.impl;

import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.comment.dto.CommentDto;
import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import com.club.masterpiece.web.comment.service.CommentCreateService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.exception.EmptyResultException;
import com.club.masterpiece.web.user.model.User;
import com.club.masterpiece.web.util.CommentIdGenerator;
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
public class CommentCreateServiceImpl implements CommentCreateService {

    private final CommentIdGenerator commentIdGenerator;
    private final CommentRepository commentRepository;
    private final CommentFindService commentFindService;

    @Override
    public CommentDto.OneResponse createParentComment(final User user,
                                                      final String articleId,
                                                      final CommentDto.CreateRequest dto) {

        log.debug("User ID : {}", user.getId());
        log.debug("Article ID : {}", articleId);
        log.debug("Comment content : {}", dto.getContent());

        Article article = Article.builder()
                .articleId(articleId)
                .build();

        Comment comment = Comment.builder()
                .commendId(commentIdGenerator.generateId())
                .user(user)
                .content(dto.getContent())
                .article(article)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return new CommentDto.OneResponse(savedComment);
    }

    @Override
    public CommentDto.OneResponse createChildComment(final User user,
                                                     final String commentId,
                                                     final CommentDto.CreateRequest dto) {

        log.debug("User ID : {}", user.getId());
        log.debug("Comment ID : {}", commentId);
        log.debug("Comment content : {}", dto.getContent());

        Comment foundComment = commentRepository.findOneByCommendIdAnd(commentId)
                .orElseThrow(() -> new EmptyResultException("Comment is Not Found."));

        Comment reply = Comment.builder()
                .commendId(commentIdGenerator.generateId())
                .user(user)
                .content(dto.getContent())
                .article(foundComment.getArticle())
                .build();

        Comment savedReply = commentRepository.save(reply);

        return null;
    }
}
