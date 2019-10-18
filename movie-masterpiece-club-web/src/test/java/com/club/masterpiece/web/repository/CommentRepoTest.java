package com.club.masterpiece.web.repository;

import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by pasudo123 on 2019-10-16
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public class CommentRepoTest {

    private Logger LOGGER = LoggerFactory.getLogger(CommentRepoTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 댓글_조회_테스트() {

        /**
         * reg_date 의 @OrderBy 를 확인한다.
         */

        String articleId = "bbb4ee415bec4fbeb44a3c498f7d4d95";

        List<Comment> commentList = commentRepository.findAllByArticleArticleIdAndCommentNullOrderByRegDateAsc(articleId);

        for(Comment comment : commentList) {
            LOGGER.info("comment : {} | {}", comment.getRegDate(), comment.getContent());

            comment.getReplyList();
            for(Comment reply : comment.getReplyList()) {
                LOGGER.info("reply : {} | {}", reply.getRegDate(), reply.getContent());
            }
        }
    }
}
