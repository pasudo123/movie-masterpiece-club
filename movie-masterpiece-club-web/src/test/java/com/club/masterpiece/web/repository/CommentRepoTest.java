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
    public void 답글_조회_테스트() {

        Comment comment = commentRepository.findOneByCommendId("277989c57cac47e3aaeb3fa5ba380b8d")
                .get();

        LOGGER.info("Article Select");
        comment.getArticle();

        LOGGER.info("Reply : getReplyList()");
        List<Comment> replyList = comment.getReplyList();

        LOGGER.info("Reply Size : size()");
        int size = replyList.size();
        LOGGER.info("Reply Size : {}", size);


        for(Comment reply : replyList) {
            LOGGER.info("Reply : {}", reply);
            reply.getReplyList();
        }
    }
}
