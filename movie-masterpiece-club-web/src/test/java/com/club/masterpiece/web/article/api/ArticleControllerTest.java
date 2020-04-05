package com.club.masterpiece.web.article.api;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.article.model.ArticleType;
import com.club.masterpiece.web.annotation.WithMockOAuthUser;
import com.club.masterpiece.web.article.service.ArticleCreateService;
import com.club.masterpiece.web.article.service.ArticleDeleteService;
import com.club.masterpiece.web.article.service.ArticleFindService;
import com.club.masterpiece.web.article.service.ArticleUpdateService;
import com.club.masterpiece.web.comment.service.CommentCreateService;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.config.security.CustomSecurityConfiguration;
import com.club.masterpiece.web.config.security.SecurityOAuth2UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ArticleController.class)
@Import(CustomSecurityConfiguration.class)
public class ArticleControllerTest {

    /** security **/
    @MockBean private SecurityOAuth2UserService securityOAuth2UserService;
    @MockBean private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired private ObjectMapper mapper;
    @Autowired private MockMvc mvc;
    @MockBean private ArticleCreateService articleCreateService;
    @MockBean private ArticleFindService articleFindService;
    @MockBean private ArticleUpdateService articleUpdateService;
    @MockBean private ArticleDeleteService articleDeleteService;
    @MockBean private CommentCreateService commentCreateService;
    @MockBean private CommentFindService commentFindService;

    @WithMockOAuthUser(id = "foo", email = "bar", roles = {"GENERAL_USER"})
    @Test
    public void createArticleTest() throws Exception {

        // given
        ArticleDto.CreateRequest dto = ArticleDto.CreateRequest.builder()
                .title("첫번째 글")
                .content("<p>안녕하세요</p>")
                .type(ArticleType.GENERAL)
                .build();

        when(articleCreateService.create(Mockito.any(), Mockito.any()))
                .thenReturn(new ArticleDto.OneResponse("&lt;p&gt;안녕하세요&lt;/p&gt;"));

        // when & then
        mvc.perform(post("/api/article")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dto)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("<p>안녕하세요</p>")));
    }
}