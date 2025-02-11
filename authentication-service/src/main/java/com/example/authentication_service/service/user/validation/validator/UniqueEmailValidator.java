package com.example.authentication_service.service.user.validation.validator;

import com.example.authentication_service.service.user.UserService;
import com.example.authentication_service.service.user.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && !email.isBlank() && !userService.existsUserByEmail(email.toLowerCase());
    }
}
