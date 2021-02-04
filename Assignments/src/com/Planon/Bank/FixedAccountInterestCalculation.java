package com.Planon.Bank;

public class FixedAccountInterestCalculation implements InterestCalculationStrategy {

	@Override
	public double getInterest(double principalAmount) {
		return principalAmount*(BankConstants.FIXED_RATE/100);
	}

}
