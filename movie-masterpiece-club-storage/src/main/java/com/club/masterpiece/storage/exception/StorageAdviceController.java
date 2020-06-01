package com.club.masterpiece.storage.exception;

import com.club.masterpiece.storage.exception.dto.ErrorResponse;
import com.club.masterpiece.storage.image.exception.ImageSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class StorageAdviceController {

    @ExceptionHandler(ImageSaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse imageSaveExceptionHandler(HttpServletRequest request, ImageSaveException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
