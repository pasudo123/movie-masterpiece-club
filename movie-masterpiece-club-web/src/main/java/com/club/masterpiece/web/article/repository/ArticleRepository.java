package com.club.masterpiece.web.article.repository;

import com.club.masterpiece.web.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> findAllByOrderByRegDateDesc();

    Optional<Article> findOneByArticleId(String articleId);

}
