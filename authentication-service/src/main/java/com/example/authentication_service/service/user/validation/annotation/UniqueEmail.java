package com.example.authentication_service.service.user.validation.annotation;

import com.example.authentication_service.service.user.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "email is busy by another user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
