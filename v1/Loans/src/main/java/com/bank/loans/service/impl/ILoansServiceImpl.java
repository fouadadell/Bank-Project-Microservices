package com.bank.loans.service.impl;

import com.bank.loans.constant.LoansConstants;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;
import com.bank.loans.exception.LoansAlreadyExistException;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.mapper.CardsMapper;
import com.bank.loans.repository.LoansRepository;
import com.bank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ILoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoansAlreadyExistException("loan","mobileNumber",mobileNumber);
        }
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        loansRepository.save(newLoan);
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("loan", "mobileNumber", mobileNumber)
        );
        return  CardsMapper.toLoansDto(loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans cards = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", loansDto.getLoanNumber()));
        loansRepository.save(CardsMapper.toLoans(loansDto));
        return  true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
