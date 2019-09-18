package com.club.masterpiece.web.movie.service;

import com.club.masterpiece.web.movie.dto.MovieDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-09-17
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MovieParseServiceTest {

    @Test
    public void JSON_파싱_테스트() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        MovieDto.EnrollRequest enrollRequest = MovieDto
                .EnrollRequest
                .builder()
                .title("movie_title")
                .summary("movie_summary")
                .date(LocalDateTime.now())
                .director("movie_director")
                .build();

        String jsonString = mapper.writeValueAsString(enrollRequest);

        System.out.println("JSON String :: " + jsonString);

    }
}
