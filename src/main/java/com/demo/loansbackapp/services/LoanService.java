package com.demo.loansbackapp.services;


import com.demo.loansbackapp.dto.LoanResponseWithUsername;
import com.demo.loansbackapp.dto.NewLoanRequest;
import com.demo.loansbackapp.enums.Status;
import com.demo.loansbackapp.mappers.LoanMapper;
import com.demo.loansbackapp.dto.LoanResponse;
import com.demo.loansbackapp.entities.Loan;
import com.demo.loansbackapp.entities.User;
import com.demo.loansbackapp.mappers.LoanRequestMapper;
import com.demo.loansbackapp.mappers.LoanWithUserMapper;
import com.demo.loansbackapp.repos.LoanRepository;
import com.demo.loansbackapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final LoanWithUserMapper loanWithUserMapper;
    private final LoanRequestMapper loanRequestMapper;
    private final UserRepository userRepository;


    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }


    public List<LoanResponse> getAllLoansByUserId(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
        List<Loan> loans = loanRepository.findAllByUserId(user.getId());
        return loans.stream().map(loanMapper::convert).toList();
    }

    public Loan saveLoan(NewLoanRequest loanRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return saveLoan(loanRequest, username);
    }

    public Loan saveLoan(NewLoanRequest loanRequest, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Loan loanDb = loanRequestMapper.convert(loanRequest);
        loanDb.setUser(user);
        loanDb.setLoanNumber(generateID().toString());
        return loanRepository.save(loanDb);
    }

    public void changeStatus(String loanNumber, Status status) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(RuntimeException::new);
        loan.setStatus(status);
        loanRepository.save(loan);
    }
    public void deleteLoan(String loanNumber) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(RuntimeException::new);
        loanRepository.delete(loan);
    }

    public static Long generateID() {
        return UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
    }

    public List<LoanResponseWithUsername> getAllLoans() {
        return loanRepository.findAll().stream().map(loanWithUserMapper::convert).toList();
    }
}

