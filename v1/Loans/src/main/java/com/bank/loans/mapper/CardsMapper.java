package com.bank.loans.mapper;

import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;

public class CardsMapper {
    public static LoansDto toLoansDto(Loans loans) {
        LoansDto loansDto = new LoansDto();
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    public static Loans toLoans(LoansDto loansDto) {
        Loans loans= new Loans();
        loans.setLoanType(loansDto.getLoanType());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
