package com.example.authentication_service.controller.security;

import com.example.authentication_service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email){
        userService.existsUserByEmail(email);
        return ResponseEntity.ok("login");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(){
        return ResponseEntity.ok("registration");
    }
}
