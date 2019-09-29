package com.club.masterpiece.web.article.repository;

import com.club.masterpiece.web.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleRepository extends JpaRepository<Article, String> {
}