package com.Planon.Bank;

import java.util.Map;
import java.util.logging.Logger;


//factory design pattern
public interface BankFunctions {
	Map<String, Double> sumAllCreditsOrDebitsOfEachSource(TransactionType debitOrCredit);
	void getEachMonthBalance();
	//void getInteresetOfAnAccount();
	void getQuaterlyInteresetOfAnAccount();
	BankConstants constants = new BankConstants();
	
	static double calculateInterest(double principalAmount, float interestRate) {
		return principalAmount * (interestRate/100);
	}
}
