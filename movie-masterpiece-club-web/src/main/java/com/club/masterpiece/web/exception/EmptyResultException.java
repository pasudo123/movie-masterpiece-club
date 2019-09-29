package com.club.masterpiece.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@AllArgsConstructor
public class EmptyResultException extends RuntimeException {

    private final String message;

}
