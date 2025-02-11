package com.example.card_service.controller.card;

import com.example.card_service.controller.card.advice.handler.CardControllerExceptionHandler;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.card.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@CardControllerExceptionHandler
public class CardController {
    private final CardService cardService;

    @PostMapping("/registration")
    public ResponseEntity<String> registerCard(@Valid @RequestBody CardRegistrationDTO cardRegistrationDTO){
        cardService.registerCard(cardRegistrationDTO);
        return ResponseEntity.ok("registered");
    }
}
