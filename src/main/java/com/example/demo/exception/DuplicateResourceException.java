package com.example.demo.exception;

public class DuplicateResourceException extends AppException{

    public DuplicateResourceException(String message){
        super("Duplicate_Resource", message);
    }
}
