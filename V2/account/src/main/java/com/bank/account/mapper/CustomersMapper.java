package com.bank.account.mapper;
import com.bank.account.dto.CustomersDto;
import com.bank.account.entity.Customers;

public class CustomersMapper {

    public static CustomersDto toCustomersDto(Customers customers) {
        CustomersDto customersDto = new CustomersDto();
        customersDto.setName(customers.getName());
        customersDto.setEmail(customers.getEmail());
        customersDto.setMobileNumber(customers.getMobileNumber());
        return customersDto;
    }

    public static Customers toCustomers(CustomersDto customersDto) {
        Customers customers = new Customers();
        customers.setName(customersDto.getName());
        customers.setEmail(customersDto.getEmail());
        customers.setMobileNumber(customersDto.getMobileNumber());
        return customers;
    }
}