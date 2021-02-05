package com.Planon.Bank;

public class CurrentAccountInterestCalculation implements InterestCalculationStrategy {

	@Override
	public double getInterest(double principalAmount) {
		return principalAmount*(BankConstants.CURRENT_RATE/100);
	}

}
