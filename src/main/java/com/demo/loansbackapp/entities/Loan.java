package com.demo.loansbackapp.entities;


import com.demo.loansbackapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String loanNumber;
    private Double amount;
    private Double totalCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;

    @ManyToOne
    @JsonIgnore
    private User user;


}
