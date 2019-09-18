package com.club.masterpiece.web.movie.api;

import com.club.masterpiece.web.movie.dto.MovieDto;
import com.club.masterpiece.web.movie.service.MovieParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-09-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieParseService movieParseService;

    @PostMapping("parse")
    public ResponseEntity<Object> parseToJson(@RequestBody MovieDto.EnrollRequest dto){

        movieParseService.parse2Json(dto);

        return ResponseEntity.ok().body("ok");
    }

}
