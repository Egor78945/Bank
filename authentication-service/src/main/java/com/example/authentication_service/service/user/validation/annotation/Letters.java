package com.example.authentication_service.service.user.validation.annotation;

import com.example.authentication_service.service.user.validation.validator.LettersValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LettersValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Letters {
    String message() default "the string must to contains only letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
