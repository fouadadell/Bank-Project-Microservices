package com.bank.loans.service;

import com.bank.loans.dto.CustomersDto;

public interface IAccountsService {

    void createAccount(CustomersDto customersDto);
    CustomersDto getAccount(String email);
    boolean updateAccount(CustomersDto customersDto);
    boolean deleteAccount(String email);

}
