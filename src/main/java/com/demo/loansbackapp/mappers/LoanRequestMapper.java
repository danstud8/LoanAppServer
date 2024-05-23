package com.demo.loansbackapp.mappers;

import com.demo.loansbackapp.dto.NewLoanRequest;
import com.demo.loansbackapp.entities.Loan;
import com.demo.loansbackapp.enums.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class LoanRequestMapper implements Converter<NewLoanRequest, Loan> {

    @Override
    public Loan convert(NewLoanRequest source) {
        return Loan.builder()
                .amount(source.getAmount())
                .totalCost(source.getTotalCost())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMonths(source.getDuration()))
                .status(Status.OPEN)
                .build();
    }
}
