package com.example.demo.exception;

import lombok.Getter;

public class AppException extends RuntimeException{

    @Getter
    private final String errorCode;

    public AppException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause){
        super(message,cause);
        this.errorCode = errorCode;
    }
}
