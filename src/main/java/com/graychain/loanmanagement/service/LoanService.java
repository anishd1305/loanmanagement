//$Id$
package com.graychain.loanmanagement.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graychain.loanmanagement.IdType;
import com.graychain.loanmanagement.dao.AggregatedDao;
import com.graychain.loanmanagement.dao.LoanDao;
import com.graychain.loanmanagement.exception.LoanException;
import com.graychain.loanmanagement.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository repository;
	
	public LoanService(LoanRepository repository) {
		this.repository = repository;
	}
	
	public List<LoanDao> getAllLoans() {
		return repository.getAllLoans();
	}

	public LoanDao addLoan(LoanDao loan) {
		return repository.addLoan(loan);
	}

	public LoanDao getLoanByLoanId(String loanId) throws LoanException {
		LoanDao loan= repository.getLoanByLoanId(loanId);
		if(loan == null) {
			throw new LoanException(LoanException.LOAN_NOT_FOUND_FOR_ID);
		}
		return loan;
	}

	public List<LoanDao> getLoanByCustomerId(String customerId) throws LoanException {
		List<LoanDao> loan = repository.getLoanByCustomerId(customerId);
		if(loan == null) {
			throw new LoanException(LoanException.LOAN_NOT_FOUND_FOR_ID);
		}
		return loan;
	}

	public List<LoanDao> getLoanByLenderId(String lenderId) throws LoanException {
		List<LoanDao> loan = repository.getLoanByLenderId(lenderId);
		if(loan == null) {
			throw new LoanException(LoanException.LOAN_NOT_FOUND_FOR_ID);
		}
		return loan;
	}
	
	public HashMap<String, AggregatedDao> getAggregatedLoans(IdType idType) throws ParseException {
		List<LoanDao> allLoans = repository.getAllLoans();
		HashMap<String, AggregatedDao> map = aggregateAmount(allLoans,idType);
		return map;
	}

	private HashMap<String, AggregatedDao> aggregateAmount(List<LoanDao> allLoans,IdType idType) throws ParseException {
		HashMap<String, AggregatedDao> map = new HashMap<String, AggregatedDao>();
		for(LoanDao eachLoan : allLoans) {
			AggregatedDao aggregatedValues;
			
			if(map.containsKey(idType.getId(eachLoan))) {
				aggregatedValues = map.get(idType.getId(eachLoan));
				aggregatedValues.setAggregatedRemainingAmount(aggregatedValues.getAggregatedRemainingAmount()+eachLoan.getRemainingAmount());
				aggregatedValues.setAggregatedAmountwithInterest(aggregatedValues.getAggregatedAmountwithInterest() + (eachLoan.getRemainingAmount()*(eachLoan.getInterstPerDay()/100)*getDifferenceInDate(eachLoan)));
				aggregatedValues.setAggregatedAmountWithPenalty(aggregatedValues.getAggregatedAmountWithPenalty() + (eachLoan.getRemainingAmount()*(eachLoan.getPenaltyPerDay()/100)*getDifferenceInDate(eachLoan)));
			}else {
				aggregatedValues = new AggregatedDao();
				aggregatedValues.setAggregatedRemainingAmount(eachLoan.getRemainingAmount());
				aggregatedValues.setAggregatedAmountwithInterest(eachLoan.getRemainingAmount()*(eachLoan.getInterstPerDay()/100)*getDifferenceInDate(eachLoan));
				aggregatedValues.setAggregatedAmountWithPenalty(eachLoan.getRemainingAmount()*(eachLoan.getPenaltyPerDay()/100)*getDifferenceInDate(eachLoan));
			}
			
			aggregatedValues.setTotalAmount(aggregatedValues.getAggregatedRemainingAmount()+aggregatedValues.getAggregatedAmountwithInterest()+aggregatedValues.getAggregatedAmountWithPenalty());
			map.put(idType.getId(eachLoan), aggregatedValues);
		}
		return map;
	}

	public long getDifferenceInDate(LoanDao loan) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = sdf.parse(loan.getPaymentDate());
        Date d2 = sdf.parse(loan.getDueDate());
        long difference_In_Days= ((d2.getTime() - d1.getTime())/ (1000 * 60 * 60 * 24))% 365;
		return difference_In_Days;
	}
	
	
}
