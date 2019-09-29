package com.club.masterpiece.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestControllerAdvice
@Slf4j
public class MasterpieceControllerAdvice {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(CustomValidationException exception, WebRequest webRequest){

        BindingResult bindingResult = exception.getBindingResult();

        bindingResult.getFieldErrors()
                .forEach(f -> log.debug("\nfield : {}\nmessage : {}", f.getField(), f.getDefaultMessage()));

        List<FieldError> list = bindingResult.getFieldErrors();
        List<String> details = new ArrayList<>();
        for(FieldError element : list) {
            details.add(element.getDefaultMessage());
        }

        ErrorResponse response = ErrorResponse.builder()
                .errorTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .details(details)
                .requestUri(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<ErrorResponse> handleEmptyResultException(EmptyResultException exception, WebRequest webRequest) {

        ErrorResponse response = ErrorResponse.builder()
                .errorTimestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .details(Collections.singletonList(exception.getMessage()))
                .requestUri(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
