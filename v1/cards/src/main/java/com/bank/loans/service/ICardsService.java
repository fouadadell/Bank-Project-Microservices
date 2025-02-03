package com.bank.loans.service;

import com.bank.loans.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);


    CardsDto fetchCard(String mobileNumber);


    boolean updateCard(CardsDto cardsDto);


    boolean deleteCard(String mobileNumber);
}
