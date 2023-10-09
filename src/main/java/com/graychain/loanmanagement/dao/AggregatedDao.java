//$Id$
package com.graychain.loanmanagement.dao;

public class AggregatedDao {

	private Integer aggregatedRemainingAmount;
	private float aggregatedAmountwithInterest;
	private float aggregatedAmountWithPenalty;
	private float totalAmount;
	
	public AggregatedDao() {}

	public Integer getAggregatedRemainingAmount() {
		return aggregatedRemainingAmount;
	}

	public void setAggregatedRemainingAmount(Integer aggregatedAmount) {
		this.aggregatedRemainingAmount = aggregatedAmount;
	}

	public float getAggregatedAmountwithInterest() {
		return aggregatedAmountwithInterest;
	}

	public void setAggregatedAmountwithInterest(float aggregatedAmountwithInterest) {
		this.aggregatedAmountwithInterest = aggregatedAmountwithInterest;
	}

	public float getAggregatedAmountWithPenalty() {
		return aggregatedAmountWithPenalty;
	}

	public void setAggregatedAmountWithPenalty(float f) {
		this.aggregatedAmountWithPenalty = f;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
