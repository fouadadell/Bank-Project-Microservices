package com.bank.loans.mapper;

import com.bank.loans.dto.CardsDto;
import com.bank.loans.entity.Cards;

public class CardsMapper {
    public static CardsDto toCardsDto(Cards cards) {
        CardsDto cardsDto = new CardsDto ();
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    public static Cards toCards(CardsDto cardsDto) {
        Cards cards= new Cards();
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }
}
