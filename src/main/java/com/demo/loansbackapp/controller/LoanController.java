package com.demo.loansbackapp.controller;


import com.demo.loansbackapp.dto.LoanResponse;
import com.demo.loansbackapp.dto.LoanResponseWithUsername;
import com.demo.loansbackapp.dto.NewLoanRequest;
import com.demo.loansbackapp.entities.Loan;
import com.demo.loansbackapp.enums.Status;
import com.demo.loansbackapp.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("health")
    public String healthCheck() {
        return "OK !";
    }
    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @GetMapping("")
    public List<LoanResponseWithUsername> getAllLoans() {
        return loanService.getAllLoans();
    }


    @GetMapping("/username/{username}")
    public List<LoanResponse> getAllLoansByUsername(@PathVariable String username) {
        return loanService.getAllLoansByUserId(username);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@RequestBody NewLoanRequest loanRequest) {
        return loanService.saveLoan(loanRequest);
    }

    @PostMapping("/{username}")
    public Loan createLoanForUser(@RequestBody NewLoanRequest loanRequest, @PathVariable String username) {
        return loanService.saveLoan(loanRequest, username);
    }

    @DeleteMapping("/{loanNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoanByLoanNumber(@PathVariable String loanNumber) {
        loanService.deleteLoan(loanNumber);
    }

    @PostMapping("/status")
    public void changeStatus(@RequestParam String loanNumber, @RequestParam Status status) {
        loanService.changeStatus(loanNumber, status);
    }
}
