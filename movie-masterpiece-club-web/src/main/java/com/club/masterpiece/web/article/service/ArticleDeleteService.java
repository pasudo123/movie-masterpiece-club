package com.club.masterpiece.web.article.service;

import java.util.Map;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleDeleteService {

    Map<String, Boolean> deleteOneById(String articleId);

}
