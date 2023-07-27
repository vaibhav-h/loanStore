package com.db.loan.service;

import com.db.loan.dao.LoanDao;
import com.db.loan.dao.LoanRepository;
import com.db.loan.model.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    LoanDao loanDao;

    @Autowired
    LoanRepository loanRepository;

    private Map<String, Loan> loans;
    private Map<String, List<Loan>> loansByLender;
    private Map<Integer, List<Loan>> loansByInterest;
    private Map<String, List<Loan>> loansByCustomerId;

    public boolean isValid(Loan loan){
        if(loan.getPaymentDate().isAfter(loan.getDueDate()))
        {
           return false;
         }
         return true;
    }

    public void  persist(Loan loan){
        loanRepository.save(loan);
    }


    private boolean validateDueDate(Loan loan){
        return loan.getDueDate().isBefore(LocalDate.now())  ? false:true;
    }


    public List<Loan> findAll(){

        loanRepository.findAll().stream().forEach(i -> {
            if (!validateDueDate(i)) {
                log.info("Loan due date has passed", i);

            }
        });
        return loanDao.findAll();
    }


}

