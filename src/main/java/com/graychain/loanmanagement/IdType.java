//$Id$
package com.graychain.loanmanagement;

import com.graychain.loanmanagement.dao.LoanDao;

public enum IdType {

	LENDERID{
		public String getId(LoanDao loan) {
			return loan.getLenderId();
		}
	},CUSTOMERID{
		public String getId(LoanDao loan) {
			return loan.getCustomerId();
		}
	},LOANID{
		public String getId(LoanDao loan) {
			return loan.getLoanId();
		}
	};
	
	public String getId(LoanDao loan) {
		return null;
	}
}
