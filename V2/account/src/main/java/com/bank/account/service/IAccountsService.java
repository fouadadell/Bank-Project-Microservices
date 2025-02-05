package com.bank.account.service;

import com.bank.account.dto.CustomersDto;

public interface IAccountsService {

    void createAccount(CustomersDto customersDto);
    CustomersDto getAccount(String email);
    boolean updateAccount(CustomersDto customersDto);
    boolean deleteAccount(String email);

}
