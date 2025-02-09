package com.example.authentication_service.controller.security;

import com.example.authentication_service.controller.security.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.user.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password){
        return ResponseEntity.ok(userService.authenticateUser(email, password));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody UserRegistrationDTO registrationDTO){
        userService.registerUser(registrationDTO);
        return ResponseEntity.ok("registration");
    }
}