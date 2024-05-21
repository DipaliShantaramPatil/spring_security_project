package com.microservicestask.userservice.exception;

import java.time.LocalDateTime;

public class SuccessResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String message;

    public SuccessResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

