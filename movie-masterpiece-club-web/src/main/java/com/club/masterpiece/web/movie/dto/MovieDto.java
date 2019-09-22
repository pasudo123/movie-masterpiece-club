package com.club.masterpiece.web.movie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
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
    public static class EnrollRequest {

        private String title;
        private String summary;
        private String director;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime date;

        @Builder
        public EnrollRequest(String title, String summary, String director, LocalDateTime date) {
            this.title = title;
            this.summary = summary;
            this.director = director;
            this.date = date;
        }
    }
}