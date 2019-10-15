package com.club.masterpiece.web.comment.repository;

import com.club.masterpiece.web.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentRepository extends JpaRepository<Comment, String> {

    Optional<Comment> findOneByCommendId(String commentId);

    List<Comment> findAllByArticleArticleId(String articleId);

}
