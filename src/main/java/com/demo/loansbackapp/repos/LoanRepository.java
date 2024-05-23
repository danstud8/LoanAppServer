package com.demo.loansbackapp.repos;

import com.demo.loansbackapp.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByUserId(Long userId);
    Optional<Loan> findByLoanNumber(String number);
}
