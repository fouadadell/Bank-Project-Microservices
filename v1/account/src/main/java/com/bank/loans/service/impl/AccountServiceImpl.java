package com.bank.loans.service.impl;

import com.bank.loans.constant.AccountsConstants;
import com.bank.loans.dto.AccountsDto;
import com.bank.loans.dto.CustomersDto;
import com.bank.loans.entity.Accounts;
import com.bank.loans.entity.Customers;
import com.bank.loans.exception.CustomerAlreadyExistException;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.mapper.AccountsMapper;
import com.bank.loans.mapper.CustomersMapper;
import com.bank.loans.repository.AccountsRepository;
import com.bank.loans.repository.CustomerRepository;
import com.bank.loans.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomersDto customersDto) {
        Optional<Customers> OptCustomer = customerRepository.findByEmail(customersDto.getEmail());
        if (OptCustomer.isPresent()) {
            throw new CustomerAlreadyExistException(
                    "Customer",
                    "email",
                    customersDto.getEmail()
            );
        }

            long accountNUmber = 1000000000L + new Random().nextInt(900000000);
            Accounts newAccount = new Accounts();
            newAccount.setAccountNumber(accountNUmber);
            newAccount.setAccountType(AccountsConstants.SAVINGS_ACCOUNT);
            newAccount.setBranchAddress(AccountsConstants.ADDRESS);
            accountsRepository.save(newAccount);
            Customers customer = CustomersMapper.toCustomers(customersDto);
            customer.setAccounts(newAccount);
            customerRepository.save(customer);
    }

    @Override
    public CustomersDto getAccount(String email) {
        Optional<Customers> OptCustomer = customerRepository.findByEmail(email);
        if (OptCustomer.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Customer",
                    "email",
                    email
            );
        }
            Optional<Accounts> OptAccounts=accountsRepository.
                    findById(OptCustomer.get().getAccounts().getAccountId());
        if (OptAccounts.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Account",
                    "accountNumber",
                    OptCustomer.get().getEmail()
            );
        }
                AccountsDto accountsDto = AccountsMapper.toAccountsDto(OptAccounts.get());
                CustomersDto customersDto = CustomersMapper.toCustomersDto(OptCustomer.get());
                customersDto.setAccountsDto(accountsDto);
                return customersDto;
    }

    @Override
    public boolean updateAccount(CustomersDto customersDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customersDto.getAccountsDto();
        if (accountsDto != null) {
            Optional<Accounts> optAccounts = accountsRepository.findByAccountNumber(accountsDto.getAccountsNumber());
            if (optAccounts.isEmpty()) {
                throw new ResourceNotFoundException(
                        "Account",
                        "accountNumber",
                        accountsDto.getAccountsNumber().toString()
                );
            }

            Accounts accounts = AccountsMapper.toAccounts(accountsDto);
            optAccounts.get().setAccountType(accounts.getAccountType());
            optAccounts.get().setBranchAddress(accounts.getBranchAddress());
            accountsRepository.save(optAccounts.get());

            Optional<Customers> optCustomer = customerRepository.findByEmail(customersDto.getEmail());
            if (optCustomer.isEmpty()) {
                throw new ResourceNotFoundException(
                        "Customer",
                        "email",
                        customersDto.getEmail()
                );
            }
            Customers customers = CustomersMapper.toCustomers(customersDto);
            optCustomer.get().setName(customers.getName());
            optCustomer.get().setMobileNumber(customers.getMobileNumber());
            optCustomer.get().setEmail(customers.getEmail());
            customerRepository.save(optCustomer.get());
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String email) {
        Optional<Customers> OptCustomer = customerRepository.findByEmail(email);
        if (OptCustomer.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Customer",
                    "email",
                    email
            );
        }
        accountsRepository.deleteById(OptCustomer.get().getAccounts().getAccountId());
        customerRepository.delete(OptCustomer.get());
        return true;
    }
}
