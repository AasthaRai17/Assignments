package com.Planon.Bank;

public class SavingAccountInterestCalculation implements InterestCalculationStrategy {

	@Override
	public double getInterest(double principalAmount) {
		return principalAmount*(BankConstants.SAVING_RATE/100);
	}

}
