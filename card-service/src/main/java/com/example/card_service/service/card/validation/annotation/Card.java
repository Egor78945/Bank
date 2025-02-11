package com.example.card_service.service.card.validation.annotation;

import com.example.card_service.service.card.validation.validator.CardValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CardValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Card {
    String message() default "unknown card type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
