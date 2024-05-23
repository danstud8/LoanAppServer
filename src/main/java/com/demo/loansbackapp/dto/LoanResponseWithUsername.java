package com.demo.loansbackapp.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoanResponseWithUsername {

    private String loanNumber;
    private Double amount;
    private Double totalCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String username;
}
