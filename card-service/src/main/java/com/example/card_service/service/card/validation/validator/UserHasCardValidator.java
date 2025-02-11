package com.example.card_service.service.card.validation.validator;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.user.grpc.UserGrpcClientService;
import com.example.card_service.service.user.mapper.CardMapper;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import com.example.card_service.service.card.validation.annotation.HasCard;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHasCardValidator implements ConstraintValidator<HasCard, CardRegistrationDTO> {
    private final UserGrpcClientService userGrpcClientService;
    @Override
    public boolean isValid(CardRegistrationDTO cardRegistrationDTO, ConstraintValidatorContext constraintValidatorContext) {
        CardType cardType = CardMapper.mapTo(cardRegistrationDTO.cardType().toLowerCase());
        return cardType != null && !userGrpcClientService.existsCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardType.getId(), cardRegistrationDTO.userId())).getBoolean();
    }
}
