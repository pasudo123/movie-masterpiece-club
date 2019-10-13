package com.club.masterpiece.web.model;

import com.club.masterpiece.web.article.model.Article;
import com.club.masterpiece.web.article.model.ArticleType;
import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import com.club.masterpiece.web.user.model.Role;
import com.club.masterpiece.web.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaAuditing
@ActiveProfiles
public class CommentJpaTest {

    private Logger LOGGER = LoggerFactory.getLogger(CommentJpaTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    private String userId = "test_user_id";
    private User user;

    private String articleId = "test_article_id";
    private Article article;

    @Before
    @Commit
    public void init() {

        user = User.builder()
                .lastLoginDate(LocalDateTime.now())
                .id(userId)
                .username("test_name")
                .role(Role.GENERAL_USER)
                .profile("test_profile")
                .email("test_email")
                .build();

        article = Article.builder()
                .type(ArticleType.GENERAL)
                .user(user)
                .content("test_content")
                .title("test_title")
                .articleId(articleId)
                .build();

        testEntityManager.persist(user);
        testEntityManager.persist(article);
        testEntityManager.flush();
        testEntityManager.clear();

        LOGGER.info("===== 여기까지 @Before 어노테이션이 작동되었다.");
    }

    @Test
    @Commit
    public void _saveAndFindTest() {

        /** user ID 만 부여 **/
        User tempUser = User.builder()
                .id(userId)
                .build();

        /** article ID 만 부여 **/
        Article tempArticle = Article.builder()
                .articleId(articleId)
                .build();

        Comment comment = Comment.builder()
                .commendId("test_comment_id")
                .article(tempArticle)
                .user(tempUser)
                .content("test_content")
                .build();

        testEntityManager.persist(comment);
        testEntityManager.flush();
        testEntityManager.clear();

        LOGGER.info("===== Comment 엔티티를 저장하였다.");
        LOGGER.info("==================================================================");


        // 1
        LOGGER.info("===== Comment 엔티티 AssertThat 수행");

        /** 영속성 컨텍스트 clear 이후에 find().get() 호춯 **/
        Comment foundComment = commentRepository.findById("test_comment_id").get();

        assertThat(foundComment.getCommendId(), is(comment.getCommendId()));
        assertThat(foundComment.getContent(), is(comment.getContent()));

        // 2
        LOGGER.info("===== User 엔티티 AssertThat 수행");

        /** foundComment 에서 user 조회 **/
        User foundUser = foundComment.getUser();

        assertThat(foundUser.getId(), is(user.getId()));
        assertThat(foundUser.getUsername(), is(user.getUsername()));
        assertThat(foundUser.getProfile(), is(user.getProfile()));
        assertThat(foundUser.getEmail(), is(user.getEmail()));

        // 3
        LOGGER.info("===== Article 엔티티 AssertThat 수행");

        /** foundComment 에서 article 조회 **/
        Article foundArticle = foundComment.getArticle();

        assertThat(foundArticle.getArticleId(), is(article.getArticleId()));
        assertThat(foundArticle.getTitle(), is(article.getTitle()));
        assertThat(foundArticle.getContent(), is(article.getContent()));

    }
}
