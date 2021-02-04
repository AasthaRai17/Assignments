package com.Planon.Bank;

public class DefaultAccountInterestCalculation implements InterestCalculationStrategy {

	@Override
	public double getInterest(double principalAmount) {
		return 0;
	}

}
