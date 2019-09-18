package com.club.masterpiece.web.movie.service.impl;

import com.club.masterpiece.web.movie.dto.MovieDto;
import com.club.masterpiece.web.movie.service.MovieParseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-09-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@Transactional
public class MovieParseServiceImpl implements MovieParseService {

    @Override
    public String parse2Json(MovieDto.EnrollRequest dto) {

        ObjectMapper mapper = new ObjectMapper();

        return null;
    }
}
