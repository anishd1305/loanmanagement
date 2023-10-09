//$Id$
package com.graychain.loanmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.graychain.loanmanagement.IdType;
import com.graychain.loanmanagement.dao.AggregatedDao;
import com.graychain.loanmanagement.dao.LoanDao;
import com.graychain.loanmanagement.exception.LoanException;
import com.graychain.loanmanagement.service.LoanService;

@RestController
public class LoanController {

	@Autowired
	LoanService service;
	
	@GetMapping(path = "/loans")
	public List<LoanDao> getAllLoans(){
		return service.getAllLoans();
	}
	
	@PostMapping(path = "/loans/add")
	public LoanDao addLoan(@RequestBody LoanDao loan) throws Exception{
		long remainingDays = service.getDifferenceInDate(loan);
        if(remainingDays<0) {
        	throw new LoanException(LoanException.INVALID_PAYMENT_DUE_DATES);
        }
		return service.addLoan(loan);
	}
	
	@GetMapping(path = "/loans_by_loanid/{loanId}")
	public LoanDao getLoanByLoanId(@PathVariable String loanId) throws LoanException {
		return service.getLoanByLoanId(loanId);
	}
	
	@GetMapping(path = "/loans_by_cid/{customerId}")
	public List<LoanDao> getLoanByCustomerId(@PathVariable String customerId) throws LoanException {
		return service.getLoanByCustomerId(customerId);
	}
	
	@GetMapping(path = "/loans_by_lenderid/{lenderId}")
	public List<LoanDao> getLoanByLenderId(@PathVariable String lenderId) throws LoanException {
		return service.getLoanByLenderId(lenderId);
	}
	
	@GetMapping(path = "/loans/aggregate/lender")
	public HashMap<String, AggregatedDao> getAggregatedLoansByLender() throws Exception{
		return service.getAggregatedLoans(IdType.LENDERID);
	}
	
	@GetMapping(path = "/loans/aggregate/customer")
	public HashMap<String, AggregatedDao> getAggregatedLoansByCustomer() throws Exception{
		return service.getAggregatedLoans(IdType.CUSTOMERID);
	}
	
	@GetMapping(path = "/loans/aggregate/loan")
	public HashMap<String, AggregatedDao> getAggregatedLoansByLoan() throws Exception{
		return service.getAggregatedLoans(IdType.LOANID);
	}
	
}
