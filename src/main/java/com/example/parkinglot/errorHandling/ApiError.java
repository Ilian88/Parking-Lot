package com.example.parkinglot.errorHandling;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ApiError {
    private String message;
    private String errorCode;

    private LocalDateTime date;

    public ApiError(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }


    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ApiError setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ApiError setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
