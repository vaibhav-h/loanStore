package com.db.loan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Loans")
public class Loan {

    @Id
    private String loanId;

    private String customerId;

    private String lenderId;

    private String amount;

    private String remainingAmount;

    private LocalDate paymentDate;

    private Integer interest;

    private LocalDate dueDate;

    private String penalty;

    private String cancel;
}
