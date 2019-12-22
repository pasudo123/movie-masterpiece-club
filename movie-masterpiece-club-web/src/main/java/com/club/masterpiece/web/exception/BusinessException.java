package com.club.masterpiece.web.exception;

import lombok.Getter;

/**
 * Created by pasudo123 on 2019-12-22
 * Email: oraedoa@gmail.com
 **/
@Getter
public class BusinessException extends RuntimeException{

    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

}
