package com.club.masterpiece.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime errorTimestamp;
    private LocalDate errorDate;
    private HttpStatus status;
    private List<String> details;
    private String requestUri;

    @Builder
    public ErrorResponse(LocalDateTime errorTimestamp, HttpStatus status, List<String> details, String requestUri) {
        this.errorTimestamp = errorTimestamp;
        this.errorDate = errorTimestamp.toLocalDate();
        this.status = status;
        this.details = details;
        this.requestUri = requestUri;
    }
}
