//$Id$
package com.graychain.loanmanagement.exception;

public class LoanException extends Exception{

	public static final String INVALID_PAYMENT_DUE_DATES = "Payment Date is higher than due date";
	public static final String LOAN_NOT_FOUND_FOR_ID = "No loans were found for given ID";
	
	public LoanException(String message) {
		super(message);
	}
	
}
