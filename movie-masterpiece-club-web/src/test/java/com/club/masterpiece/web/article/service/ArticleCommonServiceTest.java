//package com.club.masterpiece.web.article.service;
//
//import com.club.masterpiece.common.article.dto.ArticleDto;
//import com.club.masterpiece.common.article.model.Article;
//import com.club.masterpiece.common.article.model.ArticleType;
//import com.club.masterpiece.common.attachment.dto.ImageDto;
//import com.club.masterpiece.common.attachment.model.Attachment;
//import com.club.masterpiece.common.user.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Created by pasudo123 on 2019-12-22
// * Email: oraedoa@gmail.com
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {
//        ArticleCommonServiceImpl.class
//})
//@ActiveProfiles("test")
//public class ArticleCommonServiceTest {
//
//    @Autowired
//    private ArticleCommonService articleCommonService;
//
//    @Value("${keyword.image}")
//    private String imageKeyword;
//
//    @Test
//    public void _convertImageTagToKeywordOnContentTest() {
//
//        // given
//        final String content = "<div>123123</div>" +
//                "<img src=\"data:image/png;base64,0qBBk0MCNAAAWQI0AEBAsXgiXdbc9pp==\" style=\"\" width=\"444\">" +
//                "<span>span123123</span>";
//
//        final ArticleDto.CreateRequest dto = ArticleDto.CreateRequest
//                .builder()
//                .title("test article title")
//                .type(ArticleType.GENERAL)
//                .content(content)
//                .build();
//
//        // when
//        final String preContent = articleCommonService.convertImageTagToKeywordOnContent(dto);
//
//        // then
//        assertThat(preContent).isEqualTo("<div>123123</div>" + imageKeyword + "<span>span123123</span>");
//    }
//
//    @Test
//    public void _convertKeywordToImageTagOnContentTest() {
//
//        final String originContent = "<span>article</span><img src=\"data:image/png;base64,0qBBk0MCNAAAWQI0AEBAsXgiXdbc9pp==\" style=\"\" width=\"444\"><span>test</span>";
//
//        // given : ArticleDto.OneResponse
//        final User user = User
//                .builder()
//                .build();
//
//        final Article article = Article
//                .builder()
//                .articleId("test_article_id")
//                .title("test_article_title")
//                .content("<span>article</span>" + imageKeyword + "<span>test</span>")
//                .type(ArticleType.GENERAL)
//                .user(user)
//                .build();
//
//        final ArticleDto.OneResponse dto = new ArticleDto.OneResponse(article);
//
//        // given : AttachmentList
//        final ImageDto.CreateInfo imageDto = ImageDto.CreateInfo
//                .builder()
//                .name("test_image_name")
//                .url("D:\\gulagbu-images\\16216372563d3a0a92f902d1c3e067df-20191222183113.png")
//                .properties("{\"style\": \"\", \"width\": \"44\"}")
//                .size("12345")
//                .build();
//
//        final List<Attachment> attachmentList = new ArrayList<>(
//                Arrays.asList(
//                        new Attachment(article, imageDto)));
//
//        // when
//        articleCommonService.convertKeywordToImageTagOnContent(dto, attachmentList);
//
//        // then
//
//    }
//}
