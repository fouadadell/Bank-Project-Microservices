package com.bank.loans.service;

import com.bank.loans.dto.LoansDto;

public interface ILoansService {

    void createLoan(String mobileNumber);


    LoansDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoansDto cardsDto);


    boolean deleteLoan(String mobileNumber);
}
