package com.example.authentication_service.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("login");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(){
        return ResponseEntity.ok("registration");
    }
}
