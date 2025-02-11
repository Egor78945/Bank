package com.example.authentication_service.model.user.dto;

import com.example.authentication_service.service.user.validation.annotation.Letters;
import com.example.authentication_service.service.user.validation.annotation.Password;
import com.example.authentication_service.service.user.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.*;

public record UserRegistrationDTO(@NotNull @Email(message = "invalid email format") @UniqueEmail String email,
                                  @NotNull @Size(min = 10, max = 30, message = "password length must to be less 31 symbols and more 9") @Password String password,
                                  @NotNull @Size(min = 2, max = 20, message = "name length must to be less 21 and more 1 symbols") @Letters(message = "name must contains only letters") String name,
                                  @NotNull @Size(min = 2, max = 20, message = "surname length must to be less 21 and more 1 symbols") @Letters(message = "surname must contains only letters") String surname,
                                  @NotNull @Min(value = 14, message = "age must to be more or equal 14 years") @Max(value = 120, message = "age must to be less or equal 120 years") Integer age,
                                  @NotNull @Size(min = 3, max = 50, message = "city must to be less 51 and more 2 symbols") String city) {
}
