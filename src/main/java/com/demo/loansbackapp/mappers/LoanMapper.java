package com.demo.loansbackapp.mappers;


import com.demo.loansbackapp.dto.LoanResponse;
import com.demo.loansbackapp.entities.Loan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class LoanMapper implements Converter<Loan, LoanResponse> {
    @Override
    public LoanResponse convert(Loan source) {
        return LoanResponse.builder()
                .loanNumber(source.getLoanNumber())
                .amount(source.getAmount())
                .totalCost(source.getTotalCost())
                .status(source.getStatus().getName())
                .startTime(source.getStartTime())
                .endTime(source.getEndTime())
                .build();
    }

}
