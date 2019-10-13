package com.club.masterpiece.web.config;

import com.club.masterpiece.web.util.ArticleIdGenerator;
import com.club.masterpiece.web.util.CommentIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
public class GeneratorIdConfiguration {

    @Bean
    public ArticleIdGenerator articleIdGenerator() {
        return new ArticleIdGenerator();
    }

    @Bean
    public CommentIdGenerator commentIdGenerator() {
        return new CommentIdGenerator();
    }

}
