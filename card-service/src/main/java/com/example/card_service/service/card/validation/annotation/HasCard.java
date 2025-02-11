package com.example.card_service.service.card.validation.annotation;

import com.example.card_service.service.card.validation.validator.UserHasCardValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserHasCardValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasCard {
    String message() default "the user is already has this card";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
