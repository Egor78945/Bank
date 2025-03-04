package com.example.card_service.service.card.validation.validator;

import com.example.card_service.service.card.validation.annotation.Card;
import com.example.card_service.service.card.mapper.CardMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardValidator implements ConstraintValidator<Card, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && !s.isBlank() && CardMapper.mapTo(s.toLowerCase()) != null;
    }
}
