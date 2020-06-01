package com.club.masterpiece.storage.exception.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime now;

    @Builder
    public ErrorResponse(final String message) {
        this.message = message;
    }
}
