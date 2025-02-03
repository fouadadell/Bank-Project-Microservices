package com.bank.loans.mapper;

import com.bank.loans.dto.AccountsDto;
import com.bank.loans.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto toAccountsDto(Accounts accounts) {
    return new AccountsDto() {
        {
            this.setAccountsNumber(accounts.getAccountNumber());
            this.setAccountType(accounts.getAccountType());
            this.setBranchAddress(accounts.getBranchAddress());
        }
    };
    }

    public static Accounts toAccounts(AccountsDto accountsDto) {
        return new Accounts() {
            {
                this.setAccountNumber(accountsDto.getAccountsNumber());
                this.setAccountType(accountsDto.getAccountType());
                this.setBranchAddress(accountsDto.getBranchAddress());
            }
        };
    }
}
