package com.db.loan.dao;

import com.db.loan.model.Loan;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface LoanDao {


    public  static Map<String, Loan> loanMap =new ConcurrentHashMap<>();


    public void save(Loan loan);

    List<Loan> findAll();

    Loan findLoan(String loanId);
}
