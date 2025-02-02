package com.example.authentication_service.service.validation.user.validator;

import com.example.authentication_service.service.validation.user.annotation.Letters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LettersValidator implements ConstraintValidator<Letters, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if(string == null || string.isBlank()){
            return false;
        }
        for(char c: string.toLowerCase().toCharArray()){
            if (c < 97 || c > 122) {
                return false;
            }
        }
        return true;
    }
}
