package com.db.loan.dao;

import com.db.loan.model.Loan;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LoanDaoImpl implements LoanDao {
    @Override
    public void save(Loan loan) {
        loan.setPaymentDate(LocalDate.now());
        loanMap.put(loan.getLoanId(),loan);
    }

    @Override
    public List<Loan> findAll() {
         return loanMap.values().stream().
                 collect(Collectors.toList());
    }

    @Override
    public Loan findLoan(String loanId) {
        return loanMap.get(loanId);
    }
}
