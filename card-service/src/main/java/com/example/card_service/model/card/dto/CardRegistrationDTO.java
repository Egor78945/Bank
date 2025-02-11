package com.example.card_service.model.card.dto;

import com.example.card_service.service.card.validation.annotation.Card;
import jakarta.validation.constraints.NotNull;

public record CardRegistrationDTO(@NotNull Long userId, @Card(message = "Invalid card type. Required: debit, credit.") String cardType) {
}
