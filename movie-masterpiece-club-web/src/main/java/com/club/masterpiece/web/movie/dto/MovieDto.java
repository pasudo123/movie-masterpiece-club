package com.club.masterpiece.web.movie.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pasudo123 on 2019-09-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class MovieDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class enrollRequest {

        private String title;
        private String summary;
        private String director;
        private LocalDateTime releaseDate;
        private List<String> actors;

    }
}
