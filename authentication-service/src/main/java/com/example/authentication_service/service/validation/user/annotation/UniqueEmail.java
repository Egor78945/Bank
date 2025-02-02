package com.example.authentication_service.service.validation.user.annotation;

import com.example.authentication_service.service.validation.user.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email is busy by another user.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
