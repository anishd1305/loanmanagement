//$Id$
package com.graychain.loanmanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.graychain.loanmanagement.dao.LoanDao;

@Repository
@Transactional
public class LoanRepository {

	@Autowired
	EntityManager em;
	
	public List<LoanDao> getAllLoans() {
		return em.createNamedQuery("get_all_loans", LoanDao.class).getResultList();
	}

	public LoanDao addLoan(LoanDao loan) {
		return em.merge(loan);
	}

	public LoanDao getLoanByLoanId(String loanId) {
		return em.find(LoanDao.class, loanId);
	}

	public List<LoanDao> getLoanByCustomerId(String customerId) {
		 Query query = em.createNativeQuery("select * from loan_dao where customer_id = ?",LoanDao.class);
		 query.setParameter(1, customerId);
		 return query.getResultList();
	}

	public List<LoanDao> getLoanByLenderId(String lenderId) {
		 Query query = em.createNativeQuery("select * from loan_dao where lender_id = ?",LoanDao.class);
		 query.setParameter(1, lenderId);
		 return query.getResultList();
	}

	
}
