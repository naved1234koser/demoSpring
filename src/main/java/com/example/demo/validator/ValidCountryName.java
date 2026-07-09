package com.example.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryNameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCountryName {

    String message() default "Country name is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
