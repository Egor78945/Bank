package com.example.authentication_service.service.user.validation.annotation;

import com.example.authentication_service.service.user.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "password must to contains only letters and digits and at least 5 digits and 5 letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
