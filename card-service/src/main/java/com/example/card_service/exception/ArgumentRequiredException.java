package com.example.card_service.exception;

public class ArgumentRequiredException extends RuntimeException {
    public ArgumentRequiredException(Class<?> required, Class<?> actual){
        super(String.format("Required argument type - %s, actual - %s.", required, actual));
    }
}
