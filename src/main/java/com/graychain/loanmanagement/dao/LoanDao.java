//$Id$
package com.graychain.loanmanagement.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQuery(name = "get_all_loans",query = "select l from LoanDao l where l.cancel = false")
public class LoanDao {

	@Override
	public String toString() {
		return "LoanDao [loanId=" + loanId + ", customerId=" + customerId + "]";
	}

	@Id
	@GenericGenerator(name = "loan_id",strategy = "com.graychain.loanmanagement.repository.generator.LoanIdGenerator")
	@GeneratedValue(generator= "loan_id")
	private String loanId;
	
	@Column(nullable = false)
	@Size(min = 3,max = 10)
	private String customerId;
	
	@Column(nullable = false)
	@Size(min = 3,max = 10)
	private String lenderId;
	
	@Column(nullable=false)
	private Integer amount;
	
	@Column(nullable=false)
	private Integer remainingAmount;
	
	@Column(nullable=false)
	private String paymentDate;
	
	@Column(nullable=false)
	private String dueDate;
	
	@Column(nullable=false)
	private float interstPerDay;
	
	@Column(nullable=false)
	private float penaltyPerDay;
	
	@Column(nullable=false)
	private boolean cancel = false;
	
	public LoanDao() {}
	
	public LoanDao(String customerId, String lenderId, Integer amount, Integer remainingAmount, String paymentDate, String dueDate, float interstPerDay, float penaltyPerDay) {
		super();
		this.customerId = customerId;
		this.lenderId = lenderId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
		this.interstPerDay = interstPerDay;
		this.penaltyPerDay = penaltyPerDay;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLenderId() {
		return lenderId;
	}

	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Integer remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public float getInterstPerDay() {
		return interstPerDay;
	}

	public void setInterstPerDay(float interstPerDay) {
		this.interstPerDay = interstPerDay;
	}

	public float getPenaltyPerDay() {
		return penaltyPerDay;
	}

	public void setPenaltyPerDay(float penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	
}
