package com.db.loanStore;

import com.db.loan.controller.LoanController;
import com.db.loan.exception.InvalidLoanException;
import com.db.loan.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class LoanStoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private LoanController loanController;



	@Test
	void testLoanValidateAndStoreWhenPaymentDateIsLess() {
		try {
			LocalDate paymentDate = getLocalDate(05, 06, 2023);
			LocalDate dueDate = getLocalDate(05, 07, 2023);
			ResponseEntity responseEntity = loanController.loanValidateStore(createLoan("L1", paymentDate,dueDate));
		}catch (InvalidLoanException ie) {
			Assertions.assertEquals("Invalid Loan", ie.getMessage());
		}
	}

	@Test
	void testWhenDueDateHasPassed() {
		List<Loan> loanList =loanController.findLoansPastDueDate();
		Assertions.assertEquals(1, loanList.size());

	}

	private Loan createLoan(String loanId, LocalDate paymentDate, LocalDate dueDate){
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setCustomerId("C1");
		loan.setLenderId("LEN1");
		loan.setAmount("10000");
		loan.setRemainingAmount("1000");
		loan.setPaymentDate(paymentDate);
		loan.setInterest(1);
		loan.setDueDate(dueDate);
		loan.setPenalty("0.01");
		loan.setCancel("");
		return loan;
	}

	public static LocalDate getLocalDate(int day,int month, int year){
		LocalDate localDate = LocalDate.of(day,month,year);
		return localDate;
	}
}
