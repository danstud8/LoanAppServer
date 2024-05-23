package com.demo.loansbackapp.mappers;

import com.demo.loansbackapp.dto.LoanResponseWithUsername;
import com.demo.loansbackapp.entities.Loan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoanWithUserMapper implements Converter<Loan, LoanResponseWithUsername> {
    @Override
    public LoanResponseWithUsername convert(Loan source) {
        return LoanResponseWithUsername.builder()
                .loanNumber(source.getLoanNumber())
                .amount(source.getAmount())
                .totalCost(source.getTotalCost())
                .status(source.getStatus().getName())
                .startTime(source.getStartTime())
                .endTime(source.getEndTime())
                .username(source.getUser().getUsername())
                .build();
    }
}
