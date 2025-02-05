package com.bank.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CardsAlreadyExistException extends RuntimeException{
    public CardsAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with %s : '%s'",resourceName,fieldName,fieldValue));
    }
}
