package com.example.demo.exception;

import java.util.List;

public record ValidationErrorResponse (String code, String message, int status, List<String> errors){
}
