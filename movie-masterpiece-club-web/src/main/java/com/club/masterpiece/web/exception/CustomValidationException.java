package com.club.masterpiece.web.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

/**
 * Created by pasudo123 on 2019-09-23
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CustomValidationException extends RuntimeException{

    private final String message;
    private final BindingResult bindingResult;

    public CustomValidationException(final String message, final BindingResult bindingResult) {
        this.message = message;
        this.bindingResult = bindingResult;
    }
}
