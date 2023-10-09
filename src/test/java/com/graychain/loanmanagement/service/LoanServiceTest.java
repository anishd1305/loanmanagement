//$Id$
package com.graychain.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.graychain.loanmanagement.IdType;
import com.graychain.loanmanagement.dao.AggregatedDao;
import com.graychain.loanmanagement.dao.LoanDao;
import com.graychain.loanmanagement.exception.LoanException;
import com.graychain.loanmanagement.repository.LoanRepository;

public class LoanServiceTest {

	static LoanService service;
	
	@BeforeAll
	public static void setup() {
		LoanRepository repository = Mockito.mock(LoanRepository.class);
		LoanDao loan = new LoanDao("CUS1", "LEN1", 10000, 5000, "05/06/2023", "05/07/2023", 1.0f, 0.01f);
		loan.setLoanId("L1");
		service = new LoanService(repository);
		Mockito.when(repository.getAllLoans()).thenReturn(new ArrayList<LoanDao>() {{add(loan);}});
		Mockito.when(repository.addLoan(any(LoanDao.class))).thenReturn(loan);
		Mockito.when(repository.getLoanByLoanId(any(String.class))).thenReturn(loan);
		Mockito.when(repository.getLoanByCustomerId(any(String.class))).thenReturn(new ArrayList<LoanDao>() {{add(loan);}});
		Mockito.when(repository.getLoanByLenderId(any(String.class))).thenReturn(new ArrayList<LoanDao>() {{add(loan);}});
	}
	
	
	@Test
	public void getAllLoansTest() {
		List<LoanDao> allLoans = service.getAllLoans();
		assertNotNull(allLoans);
	}
	
	@Test
	public void addLoanTest() {
		LoanDao loan = new LoanDao("CUS1", "LEN1", 10000, 5000, "05/06/2023", "05/07/2023", 1.0f, 0.01f);
		loan = service.addLoan(loan);
		assertNotNull(loan.getLoanId());
	}
	
	@Test
	public void getLoanByIdTest() throws LoanException {
		
		List<LoanDao> loanByCustomerId = service.getLoanByCustomerId("CUS1");
		assertEquals(loanByCustomerId.get(0).getLenderId(), "LEN1","Get loan by customer id failed");
		
		LoanDao loanByLoneId = service.getLoanByLoanId("L1");
		assertEquals(loanByLoneId.getLenderId(), "LEN1","Get loan by lone ID failed");
		
		List<LoanDao> loanByLenderId = service.getLoanByLenderId("LEN1");
		assertEquals(loanByLenderId.get(0).getCustomerId(), "CUS1","Get loan by customer id failed");
		
	}
	
	@Test
	public void getAggregatedLoansTest() throws ParseException {
		
		HashMap<String, AggregatedDao> aggregatedLoansbyCustomer = service.getAggregatedLoans(IdType.CUSTOMERID);
		assertEquals(aggregatedLoansbyCustomer.get("CUS1").getTotalAmount(), 6515.0f,"Get aggregated loans by Customer failed");
		
		HashMap<String, AggregatedDao> aggregatedLoansByLender = service.getAggregatedLoans(IdType.LENDERID);
		assertEquals(aggregatedLoansByLender.get("LEN1").getTotalAmount(), 6515.0f,"Get aggregated loans by Lender failed");
		
		HashMap<String, AggregatedDao> aggregatedLoansByLoan = service.getAggregatedLoans(IdType.LOANID);
		assertEquals(aggregatedLoansByLoan.get("L1").getTotalAmount(), 6515.0f,"Get aggregated loans by Loan failed");
		
	}
	
	@Test
	public void getDifferenceInDateTest() throws ParseException {
		LoanDao loan = new LoanDao("CUS1", "LEN1", 10000, 5000, "05/06/2023", "05/07/2023", 1.0f, 0.01f);
		long differenceInDate = service.getDifferenceInDate(loan);
		assertEquals(differenceInDate, 30,"Difference in date failed");
	}
}
