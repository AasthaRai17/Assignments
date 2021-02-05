package com.Planon.Bank;

public class InterestCalculationContext {
	private InterestCalculationStrategy strategy;
	
	public InterestCalculationContext(InterestCalculationStrategy strategy) {
		this.strategy=strategy;
	}
	
	public double calculateInterest(double principalAmount) {
		return strategy.getInterest(principalAmount);
	}
}
