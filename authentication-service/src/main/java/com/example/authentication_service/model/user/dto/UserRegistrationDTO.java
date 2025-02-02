package com.example.authentication_service.model.user.dto;

import com.example.authentication_service.service.validation.user.annotation.Letters;
import com.example.authentication_service.service.validation.user.annotation.Password;
import com.example.authentication_service.service.validation.user.annotation.UniqueEmail;
import jakarta.validation.constraints.*;

public record UserRegistrationDTO(@NotNull @Email(message = "Invalid email format.") @UniqueEmail String email,
                                  @NotNull @Size(min = 10, max = 30, message = "Password length must to be less 31 symbols and more 9.") @Password String password,
                                  @NotNull @Size(min = 2, max = 20, message = "Name length must to be less 21 and more 1 symbols.") @Letters(message = "Name must contains only letters.") String name,
                                  @NotNull @Size(min = 2, max = 20, message = "Surname length must to be less 21 and more 1 symbols.") @Letters(message = "Surname must contains only letters.") String surname,
                                  @NotNull @Min(value = 14, message = "Age must to be more or equal 14 years.") @Max(value = 120, message = "Age must to be less or equal 120 years.") Integer age,
                                  @NotNull @Size(min = 3, max = 50, message = "City must to be less 51 and more 2 symbols.") String city) {
    public UserRegistrationDTO encodePassword(String encodedPassword) {
        return new UserRegistrationDTO(email, encodedPassword, name, surname, age, city);
    }
}
