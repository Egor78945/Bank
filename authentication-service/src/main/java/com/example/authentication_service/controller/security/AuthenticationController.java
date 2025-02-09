package com.example.authentication_service.controller.security;

import com.example.authentication_service.controller.security.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") @Email String email, @RequestParam("password") @Size (min = 10, max = 30, message = "Password length must to be less 31 symbols and more 9.") String password){
        return ResponseEntity.ok(userService.authenticateUser(email, password));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody UserRegistrationDTO registrationDTO){
        userService.registerUser(registrationDTO);
        return ResponseEntity.ok("registration");
    }
}