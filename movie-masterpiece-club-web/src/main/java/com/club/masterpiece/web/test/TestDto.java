package com.club.masterpiece.web.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by pasudo123 on 2019-09-25
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class TestDto {

    @Getter
    public static class Response{

        LocalDateTime localDateTime;
        LocalDate localDate;
        LocalTime localTime;

        @Builder
        public Response(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            this.localDate = localDateTime.toLocalDate();
            this.localTime = localDateTime.toLocalTime();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime endDate;

    }
}
