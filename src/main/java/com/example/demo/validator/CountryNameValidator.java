package com.example.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CountryNameValidator implements ConstraintValidator<ValidCountryName, String> {

    private static final List<String> BLACKLISTED = List.of("test", "unknown", "n/a", "null", "na");

    @Override
    public void initialize(ValidCountryName annotation){

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.isEmpty()) return true;
        return BLACKLISTED.stream()
                .noneMatch(banned -> banned.equalsIgnoreCase(value.trim()));
    }
}
