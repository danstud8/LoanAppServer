package com.demo.loansbackapp.dto;


import lombok.Data;

@Data
public class NewLoanRequest {

    private Double amount;
    private Double totalCost;
    private Integer duration;

}
