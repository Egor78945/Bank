package com.example.card_service.controller.card;

import com.example.card_service.controller.card.advice.handler.CardControllerExceptionHandler;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.card.CardService;
import com.example.card_service.service.card.router.CardServiceRouter;
import com.example.card_service.service.card.mapper.CardMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@CardControllerExceptionHandler
public class CardController {
    private final CardServiceRouter cardServiceRouter;

    @PostMapping("/registration")
    public ResponseEntity<String> registerCard(@Valid @RequestBody CardRegistrationDTO cardRegistrationDTO) {
        cardServiceRouter.getCardServiceByCardTypeId(CardMapper.mapTo(cardRegistrationDTO.cardType()).getId()).registerCard(cardRegistrationDTO);
        return ResponseEntity.ok("registered");
    }

    @PatchMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestParam("from") long fromCardId, @RequestParam("to") long toCardId, @RequestParam("amount") double amount){
        System.out.println("controller transaction");
        long cardTypeId = cardServiceRouter.getCardTypeIdByCardId(fromCardId);
        System.out.println(cardTypeId);
        CardService cardService = cardServiceRouter.getCardServiceByCardTypeId(cardTypeId);
        System.out.println(cardService);
        cardService.transaction(fromCardId, toCardId, amount);
        return ResponseEntity.ok("transaction done");
    }
}
