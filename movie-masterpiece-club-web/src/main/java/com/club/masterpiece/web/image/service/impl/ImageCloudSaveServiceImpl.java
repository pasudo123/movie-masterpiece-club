package com.club.masterpiece.web.image.service.impl;

import com.club.masterpiece.common.article.dto.ArticleDto;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.image.service.ImageSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-12-14
 * Email: oraedoa@gmail.com
 **/
@Slf4j
@Service
@Qualifier("ImageCloudSaveServiceImpl")
@RequiredArgsConstructor
public class ImageCloudSaveServiceImpl implements ImageSaveService {

    @Override
    public Object saveImage(User user, ArticleDto.CreateRequest dto) {

        log.info("Cloud Image Save");

        return null;
    }
}
