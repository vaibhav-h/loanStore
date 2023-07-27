package com.db.loan.controller;

import com.db.loan.exception.InvalidLoanException;
import com.db.loan.model.Loan;
import com.db.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping("/loan")
    public ResponseEntity<String> loanValidateStore(@RequestBody Loan loan){
       if(loanService.isValid(loan)) {
           loanService.persist(loan);
       }else{
           throw new InvalidLoanException(loan.getLoanId()+"  Payment date after due date");
       }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/loans")
    public List<Loan> findLoansPastDueDate(){
        return loanService.findAll();
    }
}
