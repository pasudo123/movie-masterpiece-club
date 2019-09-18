package com.club.masterpiece.web.movie.service;

import com.club.masterpiece.web.movie.dto.MovieDto;

/**
 * Created by pasudo123 on 2019-09-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface MovieParseService {

    String parse2Json(MovieDto.EnrollRequest dto);

}
