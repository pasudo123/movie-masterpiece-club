package com.club.masterpiece.web.model;

import com.club.masterpiece.common.article.model.Article;
import com.club.masterpiece.common.article.model.ArticleType;
import com.club.masterpiece.common.article.repository.ArticleRepository;
import com.club.masterpiece.common.user.model.Role;
import com.club.masterpiece.common.user.model.User;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaAuditing
@ActiveProfiles("dev")
public class ArticleJpaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleJpaTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ArticleRepository articleRepository;

    private String userId = "test_user_id";
    private User user;

    @Before
    public void init() {

        user = User.builder()
                .lastLoginDate(LocalDateTime.now())
                .id(userId)
                .username("test_name")
                .role(Role.GENERAL_USER)
                .profile("test_profile")
                .email("test_email")
                .build();

        testEntityManager.persist(user);
        testEntityManager.flush();
        testEntityManager.clear();

        LOGGER.info("===== 여기까지 @Before 어노테이션이 작동되었다.");
    }

    @Test
    public void _saveAndFindTest() {

        // user 에 ID 만 부여한 상태
        User tempUser = User.builder()
                .id(userId)
                .build();

        Article article = Article.builder()
                .articleId("test_article_id")
                .title("test_article_title")
                .content("test_article_content")
                .user(tempUser)
                .type(ArticleType.GENERAL)
                .build();

        /** 여기서 user select & article insert 가 같이 일어남 **/
        testEntityManager.persist(article);
        testEntityManager.flush();
        testEntityManager.clear();

        LOGGER.info("==================================================================");

        Article foundArticle = articleRepository.findOneById("test_article_id").get();

        LOGGER.info("저장 이전, 저장 이후 article 비교");

        // 1
        assertThat(foundArticle.getArticleId(), is(article.getArticleId()));
        assertThat(foundArticle.getTitle(), is(article.getTitle()));

        LOGGER.info("article 안에 user 비교");

        // 아티클에 저장된 유저는 id 값만 세팅된 유저 엔티티를 집어넣었음
        User foundUser = foundArticle.getUser();

        // 2
        assertThat(foundUser.getId(), is(user.getId()));
        assertThat(foundUser.getEmail(), is(user.getEmail()));
        assertThat(foundUser.getProfile(), is(user.getProfile()));
        assertThat(foundUser.getUsername(), is(user.getUsername()));
    }
}