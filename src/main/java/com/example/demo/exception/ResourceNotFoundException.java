package com.example.demo.exception;

public class ResourceNotFoundException extends AppException{

    public ResourceNotFoundException(String resourceName, Long id){
        super("Resource_Not_Found", resourceName + " not found for id : " + id);
    }
}
