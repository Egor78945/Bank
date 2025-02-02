package com.example.authentication_service.controller.security.advice;

import com.example.authentication_service.controller.security.advice.handler.AuthenticationControllerExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = AuthenticationControllerExceptionHandler.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException e) {
        var map = new HashMap<String, String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
